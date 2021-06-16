package ru.durnov.html.table;

import ru.durnov.html.CellCoordinates;

public interface SkippingCellDetector {
    boolean cellMustBeSkipped(CellCoordinates cellCoordinates);
}
