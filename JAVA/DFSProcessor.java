package algorithms;

public abstract class DFSProcessor {
    public void init(int verTexLabel, UGraphicLL g) {}
    public void finish(int verTexLabel, UGraphicLL g) {}
    public void beforePush(int verTexLabel, UGraphicLL g) {}
    public void afterPop(int verTexLabel, UGraphicLL g) {}
}
