package com.example.musicapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class Pop extends AppCompatActivity {

    private Button playButton;
    Dialog dialog;
    Dialog dialogEnd;

    public int numLeft;


    public int numRight;
    public int numSound;
    MediaPlayer mp;


    public int randomSong;
    Array array = new Array();


    int numsound;


    Random random = new Random();

    public int count = 0;

    public Pop() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.universal_pop);
        final ImageView img_test = (ImageView) findViewById(R.id.img_test);
        img_test.setClipToOutline(true);
        final ImageView img_right = (ImageView) findViewById(R.id.img_right);
        img_right.setClipToOutline(true);

        final TextView text_left = findViewById(R.id.text_test);

        final TextView text_right = findViewById(R.id.text_right);


        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //Вызов диалогового окна
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.previewdialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);

        //Кнопка закрытия диалогового окна
        TextView btnclose = (TextView) dialog.findViewById(R.id.btnclose);
        btnclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Pop.this, GameLevels.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
                dialog.dismiss();
            }
        });
        //Кнопка продолжить
        Button button = (Button) dialog.findViewById(R.id.btncontinue);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

        //_________________________________________________________
        //Вызов диалогового окна в конце - начало
        dialogEnd = new Dialog(this);
        dialogEnd.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogEnd.setContentView(R.layout.dialogendpop);
        dialogEnd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogEnd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT);
        dialogEnd.setCancelable(false);

        //Кнопка закрытия диалогового окна
        TextView btnclose2 = (TextView) dialogEnd.findViewById(R.id.btnclose);
        btnclose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Pop.this, GameLevels.class);
                    startActivity(intent);
                    finish();

                } catch (Exception e) {

                }
                dialogEnd.dismiss();
            }
        });
        //Кнопка продолжить
        Button button2 = (Button) dialogEnd.findViewById(R.id.btncontinue);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Pop.this, GameLevels.class);
                    startActivity(intent); finish();
                }catch (Exception e) {
                    e.printStackTrace();
                }

                dialogEnd.dismiss();
            }
        });
        //Вызов диалогового окна в конце - конец
        //_________________________________________________________

        Button button_back = (Button) findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Intent intent = new Intent(Pop.this, GameLevels.class);
                    startActivity(intent);
                    finish();
                } catch (Exception e) {

                }
            }
        });

        //Массив прогресса игры - начало
        final int[] progress = {
                R.id.point1, R.id.point2, R.id.point3, R.id.point4, R.id.point5,
                R.id.point6, R.id.point7, R.id.point8, R.id.point9, R.id.point10,
        };

        //Массив прогресса игры - конец

        //Анимация - начало
        final Animation a = AnimationUtils.loadAnimation(Pop.this, R.anim.alpha);
        //Анимация - конец

        int numsound = random.nextInt(array.popsounds.length);

        numLeft = random.nextInt(array.popsounds.length);
        img_test.setImageResource(array.img_4[numLeft]);
        text_left.setText(array.text4[numLeft]);

        numRight = random.nextInt(array.popsounds.length);
        while (numLeft == numRight) {
            numRight = random.nextInt(array.popsounds.length);
        }

        if (randomSong != array.popsounds[numLeft] && randomSong != array.popsounds[numRight]) {
            randomSong = random.nextInt(array.popsounds.length);
        }


        img_right.setImageResource(array.img_4[numRight]);
        text_right.setText(array.text4[numRight]);

        //Обработка нажатия на левую картинку - начало
        img_test.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_right.setEnabled(false);
                    if (array.popsounds[numLeft] == randomSong) {
                        img_test.setImageResource(R.drawable.img_true);
                    } else {
                        img_test.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Если отпустил палец - начало
                    if (array.popsounds[numLeft] == randomSong) {
                        //Если левая картинка равна
                        if (count < 10) {
                            count += 1;
                        }

                        //Закрашивание шкалы прогресса серым цветом - начало
                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашивание шкалы прогресса серым цветом - конец

                        //Закрашивание шкалы прогресса зелёным цветом - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашивание шкалы прогресса зелёным цветом - конец
                    } else {
                        //Если левая картинка не равна
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        //Закрашивание шкалы прогресса серым цветом - начало
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашивание шкалы прогресса серым цветом - конец

                        //Закрашивание шкалы прогресса зелёным цветом - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашивание шкалы прогресса зелёным цветом - конец
                    }
                    //Если опустил палец - конец
                    if (count == 10) {
                        //Выход из уровня Jazz
                        dialogEnd.show();
                        mp.stop();
                        mp.reset();
                        mp.release();
                    } else {
                        numLeft = random.nextInt(array.popsounds.length);
                        img_test.setImageResource(array.img_4[numLeft]);
                        img_test.startAnimation(a);
                        text_left.setText(array.text4[numLeft]);

                        numRight = random.nextInt(array.popsounds.length);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(array.popsounds.length);
                        }

                        if (randomSong != array.popsounds[numLeft] && randomSong != array.popsounds[numRight]) {
                            randomSong = random.nextInt(array.popsounds.length);
                        }


                        img_right.setImageResource(array.img_4[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.text4[numRight]);

                        updateMusic();
                        img_right.setEnabled(true); //Включение правой картинки
                    }

                }
                //Условие касания картинки - конец


                return true;
            }
        });
        //Обработка нажатия на левую картинку - конец

        //Обработка нажатия на правую картинку - начало
        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Условие касания картинки - начало
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //Если коснулся картинки - начало
                    img_test.setEnabled(false);
                    if (array.popsounds[numRight] == randomSong) {
                        img_right.setImageResource(R.drawable.img_true);
                    } else {
                        img_right.setImageResource(R.drawable.img_false);
                    }
                    //Если коснулся картинки - конец
                } else if (event.getAction() == MotionEvent.ACTION_UP) {
                    //Если отпустил палец - начало
                    if (array.popsounds[numRight] == randomSong) {
                        //Если правая картинка равна
                        if (count < 10) {
                            count += 1;
                        }

                        //Закрашивание шкалы прогресса серым цветом - начало
                        for (int i = 0; i < 10; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашивание шкалы прогресса серым цветом - конец

                        //Закрашивание шкалы прогресса зелёным цветом - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашивание шкалы прогресса зелёным цветом - конец
                    } else {
                        //Если правая картинка не равна
                        if (count > 0) {
                            if (count == 1) {
                                count = 0;
                            } else {
                                count -= 2;
                            }
                        }
                        //Закрашивание шкалы прогресса серым цветом - начало
                        for (int i = 0; i < 9; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points);
                        }
                        //Закрашивание шкалы прогресса серым цветом - конец

                        //Закрашивание шкалы прогресса зелёным цветом - начало
                        for (int i = 0; i < count; i++) {
                            TextView tv = findViewById(progress[i]);
                            tv.setBackgroundResource(R.drawable.style_points_green);
                        }
                        //Закрашивание шкалы прогресса зелёным цветом - конец
                    }
                    //Если опустил палец - конец
                    if (count == 10) {
                        mp.stop();
                        mp.reset();
                        mp.release();
                        dialogEnd.show();
                    } else {
                        numLeft = random.nextInt(array.popsounds.length);
                        img_test.setImageResource(array.img_4[numLeft]);
                        img_test.startAnimation(a);
                        text_left.setText(array.text4[numLeft]);

                        numRight = random.nextInt(array.popsounds.length);
                        while (numLeft == numRight) {
                            numRight = random.nextInt(array.popsounds.length);
                        }

                        if (randomSong != array.popsounds[numLeft] && randomSong != array.popsounds[numRight]) {
                            randomSong = random.nextInt(array.popsounds.length);
                        }


                        img_right.setImageResource(array.img_4[numRight]);
                        img_right.startAnimation(a);
                        text_right.setText(array.text4[numRight]);

                        //Обновление кнопки проиграть музыку - начало
                        updateMusic();
                        //Обновление кнопки проиграть музыку - конец
                        img_test.setEnabled(true); //Включение левой картинки
                    }

                }
                //Условие касания картинки - конец


                return true;
            }
        });
        //Обработка нажатия на правую картинку - конец


        // проверка, чтобы песня совпадала с numleft или numright
        while ((numsound != numLeft) && (numsound != numRight)) {
            numsound = random.nextInt(array.popsounds.length);
        }
        randomSong = array.popsounds[numsound];

        Button buttonplaymusic = findViewById(R.id.button_play_misic);
        buttonplaymusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (mp != null) {
                        mp.stop();
                        mp.release();
                    }
                    mp = MediaPlayer.create(getApplicationContext(), randomSong);
                    mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer player) {
                            player.start();
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

// Обработчик кнопки для остановки музыки
        Button buttonmusicstop = findViewById(R.id.button_music_stop);
        buttonmusicstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopMusic();
            }
        });

    }

    private void stopMusic() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    private void updateMusic() {
        numsound = random.nextInt(array.popsounds.length);
        while ((numsound != numLeft) && (numsound != numRight)) {
            numsound = random.nextInt(array.popsounds.length);
        }
        randomSong = array.popsounds[numsound];

        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }

        mp = MediaPlayer.create(getApplicationContext(), randomSong);
    }
}