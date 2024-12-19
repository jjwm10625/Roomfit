package com.example.roomfit.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.roomfit.R
import com.example.roomfit.ui.theme.Gray
import com.example.roomfit.ui.theme.OffWhite

@Composable
fun MateFilter(
    selectedHouseType: String,
    onSelectedHouseTypeChange: (String) -> Unit,
    selectedBudget: String,
    onSelectedBudgetChange: (String) -> Unit,
    selectedPeople: String,
    onSelectedPeopleChange: (String) -> Unit,
    selectedDuration: String,
    onSelectedDurationChange: (String) -> Unit
) {
    // Options for dropdowns
    val houseTypes = listOf("원룸", "빌라", "아파트")
    val budget = listOf("1000~3000만", "3000~5000만", "5000~1억", "1억 이상")
    val residentNumbers = listOf("2명", "3명", "4명 이상")
    val stayDurations = listOf("3개월~6개월", "6개월~12개월", "1년 이상")

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        MateFilterDropdown(
            label = selectedHouseType,
            options = houseTypes,
            onOptionSelected = onSelectedHouseTypeChange,
            onReset = { onSelectedHouseTypeChange("집 유형") }
        )
        MateFilterDropdown(
            label = selectedBudget,
            options = budget,
            onOptionSelected = onSelectedBudgetChange,
            onReset = { onSelectedBudgetChange("예산") }
        )
        MateFilterDropdown(
            label = selectedPeople,
            options = residentNumbers,
            onOptionSelected = onSelectedPeopleChange,
            onReset = { onSelectedPeopleChange("인원") }
        )
        MateFilterDropdown(
            label = selectedDuration,
            options = stayDurations,
            onOptionSelected = onSelectedDurationChange,
            onReset = { onSelectedDurationChange("기간") }
        )
    }
}

@Composable
fun MateFilterDropdown(
    label: String,
    options: List<String>,
    onOptionSelected: (String) -> Unit,
    onReset: () -> Unit // Reset action
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(OffWhite)
            .clickable { expanded = !expanded }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = label,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                ),
            )

            Spacer(modifier = Modifier.width(10.dp))

            Icon(
                painter = painterResource(id = R.drawable.dropdown),
                contentDescription = "Dropdown Icon",
                tint = Gray,
                modifier = Modifier.size(16.dp)
            )
        }
    }

    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
        modifier = Modifier
            .background(OffWhite)
            .fillMaxWidth(0.9f),
    ) {
        options.forEach { option ->
            DropdownMenuItem(
                onClick = {
                    onOptionSelected(option)
                    expanded = false
                },
                text = {
                    Text(text = option)
                }
            )
        }
    }
}