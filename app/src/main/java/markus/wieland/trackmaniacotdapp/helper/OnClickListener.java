package markus.wieland.trackmaniacotdapp.helper;

import markus.wieland.defaultappelements.uielements.adapter.iteractlistener.OnItemInteractListener;

public interface OnClickListener<T> extends OnItemInteractListener<T> {
    void onClick(T t);
}
