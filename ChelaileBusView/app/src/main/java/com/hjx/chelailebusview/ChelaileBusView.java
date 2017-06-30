package com.hjx.chelailebusview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hjx.chelailebusview.bean.Bus;
import com.hjx.chelailebusview.bean.BusDetail;
import com.hjx.chelailebusview.bean.LineDetail;
import com.hjx.chelailebusview.bean.Road;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Handler;

import static android.widget.LinearLayout.HORIZONTAL;
import static android.widget.LinearLayout.VERTICAL;

/**
 * Created by shaxiboy on 2017/6/27 0027.
 */

public class ChelaileBusView extends HorizontalScrollView {

    private static final int ROAD_UNIT_WIDTH = 60;
    private static final int ROAD_POSITON_Y = 100;
    private static final int ROAD_HEIGHT = 20;
    public static final int BUS_VIEW_HEIGHT = 500;
    private LineDetail lineDetail;
    private BusDetail busDetail;
    private ChelaileBusLineView lineView;
    private int stationCount;
    private int currentStation;
    private List<List<Road>> traffics;
    private List<Bus> buses;
    private Map<Integer, StationBusCondition> stationBusConditions;

    public ChelaileBusView(Context context) {
        super(context);
        init();
    }

    public ChelaileBusView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ChelaileBusView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setHorizontalScrollBarEnabled(false);
        lineView = new ChelaileBusLineView(getContext());
        addView(lineView);
    }

    public LineDetail getLineDetail() {
        return lineDetail;
    }

    public void setLineDetail(LineDetail lineDetail) {
        if (isLineDetailValid(lineDetail)) {
            this.lineDetail = lineDetail;
            stationCount = lineDetail.getStations().size();
            if (lineDetail.getTargetOrder() != 0) {
                currentStation = lineDetail.getTargetOrder();
            } else {
                currentStation = new Random().nextInt(stationCount) + 1;
            }
            traffics = lineDetail.getRoads();
            buses = lineDetail.getBuses();
            stationBusConditions = getStationBusConditions();
            busDetail = null;
            post(new Runnable() {
                @Override
                public void run() {
                    initRoad();
                    setCurrentStation(currentStation);
                    setBusOnRoad();
                    moveToStation(currentStation);
                }
            });
        }
    }

    public void setBusDetail(BusDetail busDetail) {
        if (lineDetail != null && isBusDetailValid(busDetail)) {
            this.busDetail = busDetail;
            final int previousStation = currentStation;
            currentStation = busDetail.getTargetOrder();
            traffics = busDetail.getRoads();
            buses = busDetail.getBuses();
            stationBusConditions = getStationBusConditions();
            post(new Runnable() {
                @Override
                public void run() {
                    if (previousStation != currentStation) {
                        setCurrentStation(previousStation);
                        setCurrentStation(currentStation);
                    }
                    setBusOnRoad();
                }
            });
        }
    }

    private void moveToStation(final int currentStation) {
        postDelayed(new Runnable() {
            @Override
            public void run() {
                smoothScrollTo((currentStation - 1) * 2 * ROAD_UNIT_WIDTH - getWidth() / 2, 0);
            }
        }, 1000);
    }

    private void setCurrentStation(int stationOrder) {
        LinearLayout roadContainer = (LinearLayout) lineView.getChildAt(stationOrder - 1);
        LinearLayout roadBusContainer = (LinearLayout) ((LinearLayout) roadContainer.getChildAt(1)).getChildAt(0);
        if (((!stationBusConditions.keySet().contains(stationOrder)) ||
                ((stationBusConditions.keySet().contains(stationOrder) && stationBusConditions.get(stationOrder).arrived == 0)))) {
            if (stationOrder == currentStation) {
                ImageView currentStationImageView = new ImageView(getContext());
                currentStationImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_location_current));
                roadBusContainer.addView(currentStationImageView);
            } else roadBusContainer.removeAllViews();
        }

        ImageView roadArrowImage = (ImageView) ((LinearLayout) roadContainer.getChildAt(1)).getChildAt(1);
        if (stationOrder == currentStation) {
            if (stationOrder == stationCount)
                roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_end_current));
            else
                roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_arrow_current));
        } else {
            if (stationOrder == stationCount)
                roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_end));
            else
                roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_arrow));
        }

        TextView textView = new TextView(getContext());
        int textColor = stationOrder == currentStation ? ContextCompat.getColor(getContext(), R.color.currentStationColor) : textView.getCurrentTextColor();
        float textSize = stationOrder == currentStation ? 18f : textView.getTextSize() / getResources().getDisplayMetrics().density;
        TextView stationOrderTextView = (TextView) ((LinearLayout) roadContainer.getChildAt(1)).getChildAt(2);
        stationOrderTextView.setTextColor(textColor);
        stationOrderTextView.setTextSize(textSize);

        TextView stationNameTextView = (TextView) ((LinearLayout) roadContainer.getChildAt(1)).getChildAt(3);
        stationNameTextView.setTextColor(textColor);
        stationNameTextView.setTextSize(textSize);

    }

    public int getCurrentStation() {
        return currentStation;
    }

    private boolean isLineDetailValid(LineDetail lineDetail) {
        boolean valid = true;
        if (lineDetail == null
                || lineDetail.getStations().isEmpty()
                || lineDetail.getRoads().isEmpty()
                || lineDetail.getStations().size() != (lineDetail.getRoads().size() + 1))
            valid = false;
        return valid;
    }

    private boolean isBusDetailValid(BusDetail busDetail) {
        boolean valid = true;
        if (busDetail == null
                || busDetail.getRoads().isEmpty()
                || stationCount != (busDetail.getRoads().size() + 1))
            valid = false;
        return valid;
    }

    private void initRoad() {
        lineView.removeAllViews();
        for (int stationOrder = 1; stationOrder <= stationCount; stationOrder++) {
            LinearLayout roadContainer = new LinearLayout(getContext());
            roadContainer.setOrientation(HORIZONTAL);

            LinearLayout middleRoadBusContaioner = new LinearLayout(getContext());
            middleRoadBusContaioner.setOrientation(VERTICAL);
            middleRoadBusContaioner.setVerticalGravity(Gravity.BOTTOM);
            roadContainer.addView(middleRoadBusContaioner, new LayoutParams(ROAD_UNIT_WIDTH, ROAD_POSITON_Y - 4));

            LinearLayout stationRoadContaioner = new LinearLayout(getContext());
            stationRoadContaioner.setOrientation(VERTICAL);
            stationRoadContaioner.setHorizontalGravity(Gravity.CENTER);
            LinearLayout stationRoadBusContaioner = new LinearLayout(getContext());
            stationRoadBusContaioner.setOrientation(VERTICAL);
            stationRoadBusContaioner.setVerticalGravity(Gravity.BOTTOM);
            stationRoadContaioner.addView(stationRoadBusContaioner, new LayoutParams(ROAD_UNIT_WIDTH, ROAD_POSITON_Y - 4));

            ImageView roadArrowImage = new ImageView(getContext());
            roadArrowImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
            if (stationOrder == currentStation) {
                if (stationOrder == stationCount)
                    roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_end_current));
                else
                    roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_arrow_current));
            } else {
                if (stationOrder == stationCount)
                    roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_end));
                else
                    roadArrowImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_arrow));
            }
            stationRoadContaioner.addView(roadArrowImage, new LayoutParams(ROAD_UNIT_WIDTH, ROAD_HEIGHT + 8));

            TextView stationOrderTextView = new TextView(getContext());
            stationOrderTextView.setText(stationOrder + "");
            stationOrderTextView.setGravity(Gravity.CENTER);
            stationRoadContaioner.addView(stationOrderTextView);

            TextView stationNameTextView = new TextView(getContext());
            stationNameTextView.setText(lineDetail.getStations().get(stationOrder - 1).getSn());
            stationNameTextView.setGravity(Gravity.CENTER);
            stationNameTextView.setEms(1);
            LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(0, 0, 0, 5);
            stationRoadContaioner.addView(stationNameTextView, layoutParams);

            if (!lineDetail.getStations().get(stationOrder - 1).getMetros().isEmpty()) {
                ImageView metroImageView = new ImageView(getContext());
                metroImageView.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.subway_shenzhen));
                stationRoadContaioner.addView(metroImageView);
            }


            roadContainer.addView(stationRoadContaioner);

            lineView.addView(roadContainer, new LayoutParams(ROAD_UNIT_WIDTH * 2, ViewGroup.LayoutParams.MATCH_PARENT));
        }
    }

    private void setBusOnRoad() {
        for (int station = 1; station <= stationCount; station++) {
            LinearLayout roadContainer = (LinearLayout) lineView.getChildAt(station - 1);
            ((LinearLayout) roadContainer.getChildAt(0)).removeAllViews();
            if (station != currentStation ||
                    stationBusConditions.containsKey(currentStation) && stationBusConditions.get(currentStation).arrived != 0)
                ((LinearLayout) ((LinearLayout) roadContainer.getChildAt(1)).getChildAt(0)).removeAllViews();
        }
        int nearestStation = 1;
        Map<Integer, StationBusCondition> conditions = getStationBusConditions();
        for (int station : conditions.keySet()) {
            if (station <= currentStation && station > nearestStation) nearestStation = station;
        }
        for (int station : conditions.keySet()) {
            LinearLayout roadContainer = (LinearLayout) lineView.getChildAt(station - 1);
            if (conditions.get(station).arriving != 0) {
                ImageView busImage = new ImageView(getContext());
                if (station == nearestStation && conditions.get(station).arrived == 0)
                    busImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_bus_big));
                else
                    busImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_bus));
                addBusToRoadContainer((LinearLayout) roadContainer.getChildAt(0), busImage, conditions.get(station).arriving);
            }
            if (conditions.get(station).arrived != 0) {
                ImageView busImage = new ImageView(getContext());
                if (station == nearestStation)
                    busImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_bus_big));
                else
                    busImage.setImageDrawable(ContextCompat.getDrawable(getContext(), R.drawable.line_bus));
                addBusToRoadContainer(((LinearLayout) ((LinearLayout) roadContainer.getChildAt(1)).getChildAt(0)), busImage, conditions.get(station).arrived);
            }
        }
    }

    private void addBusToRoadContainer(LinearLayout container, ImageView busImage, int busCount) {
        container.removeAllViews();
        if (busCount > 1) {
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setText(busCount + "辆");
            container.addView(textView);
        }
        container.addView(busImage);
    }

    private Map<Integer, StationBusCondition> getStationBusConditions() {
        Map<Integer, StationBusCondition> conditions = new TreeMap<>();
        for (int i = 0; i < buses.size(); i++) {
            if (!conditions.keySet().contains(buses.get(i).getOrder())) {
                conditions.put(buses.get(i).getOrder(), new StationBusCondition());
            }
            if (buses.get(i).getDistanceToSc() > 0)
                conditions.get(buses.get(i).getOrder()).arriving++;
            else conditions.get(buses.get(i).getOrder()).arrived++;
        }
        return conditions;
    }

    class ChelaileBusLineView extends LinearLayout {

        Path path = new Path();
        float roundRectRadius = ROAD_HEIGHT / 2;
        float[] roundRectStart = new float[]{roundRectRadius, roundRectRadius, 0, 0, 0, 0, roundRectRadius, roundRectRadius};
        float[] roundRectEnd = new float[]{0, 0, roundRectRadius, roundRectRadius, roundRectRadius, roundRectRadius, 0, 0};
        RectF roadUnitRectF = new RectF(ROAD_UNIT_WIDTH, ROAD_POSITON_Y, ROAD_UNIT_WIDTH, ROAD_POSITON_Y + ROAD_HEIGHT);
        Paint roadPaint = new Paint();
        int trafficColor = ContextCompat.getColor(getContext(), R.color.trafficColorGood);

        public ChelaileBusLineView(Context context) {
            super(context);
            init();
        }

        public ChelaileBusLineView(Context context, @Nullable AttributeSet attrs) {
            super(context, attrs);
            init();
        }

        public ChelaileBusLineView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
            super(context, attrs, defStyleAttr);
            init();
        }

        private void init() {
            setWillNotDraw(false);
            setOrientation(HORIZONTAL);
            roadPaint.setStyle(Paint.Style.FILL);
            roadPaint.setColor(ContextCompat.getColor(getContext(), R.color.trafficColorGood));
        }

        @Override
        protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension((stationCount * 2 + 1) * ROAD_UNIT_WIDTH, BUS_VIEW_HEIGHT);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            drawRoadLine(canvas);
        }

        private void drawRoadLine(Canvas canvas) {
            roadUnitRectF.left = ROAD_UNIT_WIDTH;
            path.reset();
            if(lineDetail != null) {
                for (int i = 0; i < traffics.size(); i++) {
                    for (int j = 0; j < traffics.get(i).size(); j++) {
                        switch (traffics.get(i).get(j).getTVL()) {
                            case 1:
                                trafficColor = ContextCompat.getColor(getContext(), R.color.trafficColorGood);
                                break;
                            case 2:
                                trafficColor = ContextCompat.getColor(getContext(), R.color.trafficColorNotGood);
                                break;
                            case 3:
                                trafficColor = ContextCompat.getColor(getContext(), R.color.trafficColorBad);
                                break;
                            case 4:
                                trafficColor = ContextCompat.getColor(getContext(), R.color.trafficColorVaryBad);
                                break;
                        }
                        roadPaint.setColor(trafficColor);
                        //此处需注意浮点数精确计算问题
                        if (i == 0 && j == 0) {
                            roadUnitRectF.right = roadUnitRectF.left + traffics.get(i).get(j).getTPC() * ROAD_UNIT_WIDTH * 2.5f;
                            path.addRoundRect(roadUnitRectF, roundRectStart, Path.Direction.CCW);
                            canvas.drawPath(path, roadPaint);
                        } else if ((i == traffics.size() - 1) && (j == traffics.get(i).size() - 1)) {
                            roadUnitRectF.right = stationCount * 2 * ROAD_UNIT_WIDTH;
                            path.reset();
                            path.addRoundRect(roadUnitRectF, roundRectEnd, Path.Direction.CCW);
                            canvas.drawPath(path, roadPaint);
                        } else {
                            if (j == traffics.get(i).size() - 1)
                                roadUnitRectF.right = ROAD_UNIT_WIDTH * ((i + 1) * 2 + 1.5f);
                            else
                                roadUnitRectF.right = roadUnitRectF.left + traffics.get(i).get(j).getTPC() * ROAD_UNIT_WIDTH * 2f;
                            canvas.drawRect(roadUnitRectF, roadPaint);
                        }
                        roadUnitRectF.left = roadUnitRectF.right;
                    }
                }
            }
        }
    }

    class StationBusCondition {
        int arriving;
        int arrived;
    }
}
