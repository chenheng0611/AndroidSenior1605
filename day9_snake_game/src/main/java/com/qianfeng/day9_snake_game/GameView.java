package com.qianfeng.day9_snake_game;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

import java.util.LinkedList;
import java.util.Random;

/**
 * 贪食蛇游戏视图
 * Created by xray on 16/10/11.
 */

public class GameView extends View {

    //格子宽高
    public static final int SIZE = 40;
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;
    //是否允许运行
    private boolean isRunning = false;
    //是否死亡
    private boolean isDead = false;
    //是否移动
    private boolean isMoving = false;
    //方向
    private int mDirection = UP;
    //蛇的身体
    private LinkedList<Point> mBody;
    //食物坐标
    private Point mFood;
    //画笔
    private Paint mPaint;
    //宽、高
    private int mWidth;
    private int mHeight;
    //分数
    private int mScore = 0;

    public GameView(Context context,int mWidth,int mHeight) {
        super(context);
        this.mWidth = mWidth;
        this.mHeight = mHeight;
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    /**
     * 初始化游戏
     */
    private void initGame(){
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(20);
        createBody();
        createFood();
        isRunning = true;
        isMoving = false;
        isDead = false;
    }

    //添加蛇身体
    private void createBody(){
        mBody = new LinkedList<>();
        for (int i = 10; i <= 15; i++) {
            mBody.add(new Point(10,i));
        }
    }

    private void createFood(){
        //食物不能出现在身体中
        do {
            Random random = new Random();
            int x = 1 + random.nextInt(mWidth / SIZE - 3);
            int y = 1 + random.nextInt(mHeight / SIZE - 4);
            mFood = new Point(x, y);
        }while(mBody.contains(mFood));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.GREEN);
        //画蛇身体
        for(Point point : mBody){
            canvas.drawRect(point.x * SIZE,point.y * SIZE,
                    point.x * SIZE + SIZE - 2,point.y * SIZE + SIZE - 2,mPaint );
        }
        mPaint.setColor(Color.YELLOW);
        //画食物
        canvas.drawRect(mFood.x * SIZE,mFood.y * SIZE,
                mFood.x * SIZE + SIZE - 1,mFood.y * SIZE + SIZE - 1,mPaint );
        //画分数
        int record = SharedPreferencesUtils.getRecord(getContext());
        mPaint.setColor(Color.RED);
        canvas.drawText("最高分:"+ record * 10+",当前分数:"+mScore * 10,10,30,mPaint);
    }

    /**
     * 设置方向
     * @param direction
     */
    public void setDirection(int direction) {
        isMoving = true;
        switch (mDirection){
            case UP:
                if(direction != DOWN){
                    this.mDirection = direction;
                }
                break;
            case DOWN:
                if(direction != UP){
                    this.mDirection = direction;
                }
                break;
            case LEFT:
                if(direction != RIGHT){
                    this.mDirection = direction;
                }
                break;
            case RIGHT:
                if(direction != LEFT){
                    this.mDirection = direction;
                }
                break;
        }

    }

    /**
     * 移动蛇
     */
    private void move(){
        Point head = mBody.getFirst();
        //创建一个新的格子,把蛇头的坐标赋值给它
        Point newHead = new Point(head.x,head.y);
        //控制新的格子坐标移动
        switch(mDirection){
            case UP:
                if(newHead.y <= 0){
                    isDead = true;
                }
                newHead.y--;
                break;
            case DOWN:
                if(newHead.y >= mHeight / SIZE - 1){
                    isDead = true;
                }
                newHead.y++;
                break;
            case LEFT:
                if(newHead.x <= 0){
                    isDead = true;
                }
                newHead.x--;
                break;
            case RIGHT:
                if(newHead.x >= mWidth / SIZE - 1){
                    isDead = true;
                }
                newHead.x++;
                break;
        }
        //咬到自己
        if(mBody.contains(newHead)){
            isDead = true;
        }
        //把新的头插入到身体中
        mBody.addFirst(newHead);
        //吃食物
        if(newHead.equals(mFood)){
            createFood();
            mScore++;
        }else {
            //删除尾巴
            mBody.removeLast();
        }
        //重新游戏
        if(isDead){
            reset();
        }
    }

    private void reset(){
        if(mScore > SharedPreferencesUtils.getRecord(getContext())){
            SharedPreferencesUtils.saveRecord(getContext(),mScore);
        }
        isDead = false;
        isRunning = true;
        isMoving = false;
        mScore = 0;
        createBody();
        createFood();
    }

    /**
     * 开始游戏
     */
    public void start(){
        initGame();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while(isRunning){
                    //移动
                    if(isMoving){
                        move();
                    }
                    //更新视图
                    postInvalidate();
                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    public void stop(){
        isRunning = false;
    }
}
