package me.Sauerbier.Antivist.FrameWork;

/**
 * @Author Sauerbier | Jan
 * @Copyright 2016 by Jan Hof
 * All rights reserved.
 **/
public abstract class MouseCallBack {

    private boolean canceled,callOnMove;

    public abstract void call(Mouse m);

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public boolean isCallOnMove() {
        return callOnMove;
    }

    public void setCallOnMove(boolean callOnMove) {
        this.callOnMove = callOnMove;
    }
}
