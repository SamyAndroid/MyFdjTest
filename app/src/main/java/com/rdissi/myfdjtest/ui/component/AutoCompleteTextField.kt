package com.rdissi.myfdjtest.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rdissi.myfdjtest.R
import com.rdissi.myfdjtest.domain.model.League

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AutoCompleteTextField(
    query: String = "",
    items: List<League> = emptyList(),
    onQueryChange: (String) -> Unit = {},
    onLeagueSelected: (League) -> Unit = {},
    onCancelSearch: () -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    val resetState = {
        onQueryChange("")
        expanded = false
        onCancelSearch()
    }
    val focusManager = LocalFocusManager.current

    val filteredItems = items.filter {
            it.name.contains(query, ignoreCase = true)
        }

    Column {
        Row(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(end = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ExposedDropdownMenuBox(
                expanded = expanded && filteredItems.isNotEmpty(),
                onExpandedChange = { expanded = it },
                modifier = Modifier.weight(1f),
            ) {
                TextField(
                    value = query,
                    onValueChange = {
                        onQueryChange(it)
                        expanded = it.isNotBlank()
                    },
                    label = { Text(text = stringResource(id = R.string.search)) },
                    trailingIcon = {
                        if (query.isNotEmpty()) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = stringResource(id = R.string.clear),
                                modifier =
                                    Modifier.clickable {
                                        resetState()
                                    }
                            )
                        }
                    },
                    maxLines = 1,
                    singleLine = true,
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .menuAnchor(MenuAnchorType.PrimaryEditable),
                )

                ExposedDropdownMenu(
                    expanded = expanded && filteredItems.isNotEmpty(),
                    onDismissRequest = { expanded = false },
                ) {
                    filteredItems.forEach { league ->
                        DropdownMenuItem(
                            text = { Text(league.name) },
                            onClick = {
                                onQueryChange(league.name)
                                expanded = false
                                onLeagueSelected(league)
                                focusManager.clearFocus()
                            },
                        )
                    }
                }
            }
            TextButton(
                onClick = resetState,
            ) {
                Text(text = stringResource(id = R.string.cancel))
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AutoCompleteTextFieldPreview() {
    AutoCompleteTextField()
}
