package com.wangdm.ui.query;

import com.wangdm.core.query.PageQuery;

public class HotspotQuery extends PageQuery {
    
    private Boolean display = null;

    public Boolean getDisplay() {
        return display;
    }

    public void setDisplay(Boolean show) {
        this.display = show;
    }

}
