package fr.radi3nt.ik.solvers;

public abstract class IkIterativeSolver implements IkSolver {

    private final int maxIterations;
    protected final float allowedMarginOfError;

    public IkIterativeSolver(int maxIterations, float allowedMarginOfError) {
        this.maxIterations = maxIterations;
        this.allowedMarginOfError = allowedMarginOfError;
    }


    @Override
    public void solve() {
        for (int i = 0; i < maxIterations; i++) {
            prepareSolve();
            if (isInAllowedMarginOfError())
                break;
            iteration();
        }
        endSolve();
    }

    protected void endSolve() {

    }

    protected void prepareSolve() {

    }

    protected abstract boolean isInAllowedMarginOfError();
    protected abstract void iteration();
}
