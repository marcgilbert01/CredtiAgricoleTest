package net.bankingapp.designSystem.atoms

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import net.bankingapp.designSystem.tokens.caColors
import net.bankingapp.designSystem.tokens.caSpacing
import net.bankingapp.designSystem.tokens.rememberChevronRight

@Composable
fun UpButton(
    label: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier.padding(caSpacing.smallSpacing).clickable { onClick() },
        verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
    ) {
        Icon(
            rememberChevronRight(),
            tint = caColors.tertiary,
            contentDescription = label,
            modifier = Modifier
                .padding(start = caSpacing.smallSpacing, end = caSpacing.smallSpacing)
                .rotate(180f),
        )
        Text(label, color = caColors.tertiary)
    }
}