package agh.mgr.mecanic;

public enum ComparisionResult {
    CONTINUE_MOVE, // to musi wynikać z porównania z poprzednim odczytem (
    ZBACZAM_Z_KURSU_POPRAW_MNIE, // jw
    X_Y_IN_RANGE_ROTATION_INCOMPLETED, // bierz kolejny punkt
    X_Y_IN_RANGE_ROTATION_OK, // poki co pewnie identycznie obsługiwane
}
