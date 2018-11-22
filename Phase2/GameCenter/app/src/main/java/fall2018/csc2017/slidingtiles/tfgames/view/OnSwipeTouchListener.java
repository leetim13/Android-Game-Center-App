package fall2018.csc2017.slidingtiles.tfgames.view;

import android.content.Context;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import fall2018.csc2017.slidingtiles.controller.MovementControllerTF;
import fall2018.csc2017.slidingtiles.tfgames.managers.BoardManagerTF;

public class OnSwipeTouchListener implements View.OnTouchListener {
    public static final int UP_SIGNAL = 0;
    public static final int RIGHT_SIGNAL = 1;
    public static final int DOWN_SIGNAL = 2;
    public static final int LEFT_SIGNAL = 3;

    private GestureDetector gestureDetector;
    private Context context;
    private BoardManagerTF boardManagerTF;
    private MovementControllerTF movementControllerTF;

    public OnSwipeTouchListener(Context c) {
        gestureDetector = new GestureDetector(c, new GestureListener());
        context = c;
    }

    public boolean onTouch(final View view, final MotionEvent motionEvent) {
        return gestureDetector.onTouchEvent(motionEvent);
    }

    private final class GestureListener extends SimpleOnGestureListener {

        private static final int SWIPE_THRESHOLD = 100;
        private static final int SWIPE_VELOCITY_THRESHOLD = 100;

        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        // Determines the fling velocity and then fires the appropriate swipe event accordingly
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            boolean result = false;
            try {
                float diffY = e2.getY() - e1.getY();
                float diffX = e2.getX() - e1.getX();
                if (Math.abs(diffX) > Math.abs(diffY)) {
                    if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffX > 0) {
                            swipe(OnSwipeTouchListener.RIGHT_SIGNAL);
                        } else {
                            swipe(OnSwipeTouchListener.LEFT_SIGNAL);
                        }
                    }
                } else {
                    if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                        if (diffY > 0) {
                            swipe(OnSwipeTouchListener.DOWN_SIGNAL);
                        } else {
                            swipe(OnSwipeTouchListener.UP_SIGNAL);
                        }
                    }
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            return result;
        }
    }

    private void swipe(int signal) {
        if (signal == OnSwipeTouchListener.UP_SIGNAL) {

            Toast.makeText(context, "You swiped Up", Toast.LENGTH_SHORT).show();
        }

        if (signal == OnSwipeTouchListener.RIGHT_SIGNAL) {
            Toast.makeText(context, "You swiped right", Toast.LENGTH_SHORT).show();
        }

        if (signal == OnSwipeTouchListener.DOWN_SIGNAL) {
            Toast.makeText(context, "You swiped down", Toast.LENGTH_SHORT).show();
        }

        if (signal == OnSwipeTouchListener.LEFT_SIGNAL) {
            Toast.makeText(context, "You swiped left", Toast.LENGTH_SHORT).show();
        }

        movementControllerTF.processTapMovement(context, signal);
    }

    public void setBoardManager(BoardManagerTF boardManager) {
        this.boardManagerTF = boardManager;
        movementControllerTF.setBoardManager(boardManager);
    }
}
