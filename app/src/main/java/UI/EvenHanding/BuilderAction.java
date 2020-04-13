package UI.EvenHanding;

import android.view.View;

public interface BuilderAction<T> {
     void builderandAddAction(T view, int eventPosition, int actionPosition);
}
