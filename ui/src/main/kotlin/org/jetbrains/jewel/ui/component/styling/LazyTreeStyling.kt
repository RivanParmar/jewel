package org.jetbrains.jewel.ui.component.styling

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import org.jetbrains.jewel.foundation.GenerateDataFunctions
import org.jetbrains.jewel.foundation.lazy.tree.TreeElementState
import org.jetbrains.jewel.ui.painter.PainterProvider

@Stable
@GenerateDataFunctions
class LazyTreeStyle(
    val colors: LazyTreeColors,
    val metrics: LazyTreeMetrics,
    val icons: LazyTreeIcons,
) {

    companion object
}

@Immutable
@GenerateDataFunctions
class LazyTreeColors(
    val elementBackgroundFocused: Color,
    val elementBackgroundSelected: Color,
    val elementBackgroundSelectedFocused: Color,
    val content: Color,
    val contentFocused: Color,
    val contentSelected: Color,
    val contentSelectedFocused: Color,
) {

    @Composable
    fun contentFor(state: TreeElementState) = rememberUpdatedState(
        when {
            state.isSelected && state.isFocused -> contentSelectedFocused
            state.isFocused -> contentFocused
            state.isSelected -> contentSelected
            else -> content
        },
    )

    companion object
}

@Stable
@GenerateDataFunctions
class LazyTreeMetrics(
    val indentSize: Dp,
    val elementBackgroundCornerSize: CornerSize,
    val elementPadding: PaddingValues,
    val elementContentPadding: PaddingValues,
    val elementMinHeight: Dp,
    val chevronContentGap: Dp,
) {

    companion object
}

@Immutable
@GenerateDataFunctions
class LazyTreeIcons(
    val chevronCollapsed: PainterProvider,
    val chevronExpanded: PainterProvider,
    val chevronSelectedCollapsed: PainterProvider,
    val chevronSelectedExpanded: PainterProvider,
) {

    @Composable
    fun chevron(isExpanded: Boolean, isSelected: Boolean) =
        when {
            isSelected && isExpanded -> chevronSelectedExpanded
            isSelected && !isExpanded -> chevronSelectedCollapsed
            !isSelected && isExpanded -> chevronExpanded
            else -> chevronCollapsed
        }

    companion object
}

val LocalLazyTreeStyle = staticCompositionLocalOf<LazyTreeStyle> {
    error("No LazyTreeStyle provided")
}