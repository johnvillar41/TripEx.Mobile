package com.tripex.tripexmobile;

public interface OnItemClick<T> {

    void onItemClick(T data);
    void onItemLongClick(T data);

}
