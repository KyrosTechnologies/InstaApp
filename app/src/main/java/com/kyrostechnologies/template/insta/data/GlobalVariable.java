package com.kyrostechnologies.template.insta.data;

import android.app.Application;

public class GlobalVariable extends Application{
    private boolean grid_mode = true;

    public boolean isGrid_mode() {
        return grid_mode;
    }

    public void setGrid_mode(boolean grid_mode) {
        this.grid_mode = grid_mode;
    }
}
