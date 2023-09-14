package fr.radi3nt.ik.solvers;

public abstract class IkIterativeSolver implements IkSolver {

    private final int maxIterations;
    protected final float allowedMarginOfError;

    private boolean solved;

    public IkIterativeSolver(int maxIterations, float allowedMarginOfError) {
        this.maxIterations = maxIterations;
        this.allowedMarginOfError = allowedMarginOfError;
    }


    @Override
    public void solve() {
        if (isInAllowedMarginOfError()) {
            solved = false;
            return;
        }
        for (int i = 0; i < maxIterations; i++) {
            iteration();
            if (isInAllowedMarginOfError())
                break;
        }
        solved = true;
    }

    public boolean iterationWasSolved() {
        return solved;
    }

    protected abstract boolean isInAllowedMarginOfError();
    protected abstract void iteration();
}
