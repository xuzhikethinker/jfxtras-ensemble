/*
 * Copyright (c) 2012, JFXtras
 *   All rights reserved.
 *
 *   Redistribution and use in source and binary forms, with or without
 *   modification, are permitted provided that the following conditions are met:
 *       * Redistributions of source code must retain the above copyright
 *         notice, this list of conditions and the following disclaimer.
 *       * Redistributions in binary form must reproduce the above copyright
 *         notice, this list of conditions and the following disclaimer in the
 *         documentation and/or other materials provided with the distribution.
 *       * Neither the name of the <organization> nor the
 *         names of its contributors may be used to endorse or promote products
 *         derived from this software without specific prior written permission.
 *
 *   THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 *   ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *   WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 *   DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 *   DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 *   (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 *   LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 *   ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *   (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 *   SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package ensemble.samples.gauges.splitflap;

import ensemble.Sample;
import java.util.Calendar;
import java.util.Random;
import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import jfxtras.labs.scene.control.gauge.SplitFlap;
import jfxtras.labs.scene.control.gauge.SplitFlapBuilder;

/**
 * FlipChar control.
 *
 * @see jxftras.labs.scene.control.gauge.FlipChar
 */
public class SplitFlapGaugesSample extends Sample {
    private static final Random  RND          = new Random();
    private static final long    TIME_PERIOD  = 1000000000l;
    private long                 lastTimeCall = 0;
    private SplitFlap            flip1;
    private SplitFlap            flip2;
    private SplitFlap            flip3;
    private SplitFlap            flip4;
    private SplitFlap            flip5;
    private SplitFlap            flip6;
    private final AnimationTimer TIMER        = new AnimationTimer() {
        @Override
        public void handle(long l) {
            long currentNanoTime = System.nanoTime();
            if (currentNanoTime > lastTimeCall + TIME_PERIOD) {
                setClock();
                lastTimeCall = System.nanoTime();
            }
        }
    };

    public SplitFlapGaugesSample() {
        super(600, 600);

        // Create some controls
        flip1 = SplitFlapBuilder.create()
                                .prefWidth(80)
                                .prefHeight(133)
                                .flipTimeInMs(350)
                                .textColor(Color.rgb(200, 200, 200))
                                .selection(SplitFlap.TIME_0_TO_5)
                                .build();

        flip2 = SplitFlapBuilder.create()
                                .prefWidth(80)
                                .prefHeight(133)
                                .flipTimeInMs(350)
                                .textColor(Color.rgb(200, 200, 200))
                                .selection(SplitFlap.TIME_0_TO_9)
                                .build();

        flip3 = SplitFlapBuilder.create()
                                .prefWidth(80)
                                .prefHeight(133)
                                .flipTimeInMs(350)
                                .textColor(Color.rgb(200, 200, 200))
                                .selection(SplitFlap.TIME_0_TO_5)
                                .build();

        flip4 = SplitFlapBuilder.create()
                                .prefWidth(80)
                                .prefHeight(133)
                                .flipTimeInMs(350)
                                .textColor(Color.rgb(200, 200, 200))
                                .selection(SplitFlap.TIME_0_TO_9)
                                .build();

        flip5 = SplitFlapBuilder.create()
                                .prefWidth(80)
                                .prefHeight(133)
                                .flipTimeInMs(350)
                                .color(Color.rgb(220, 20, 0))
                                .textColor(Color.rgb(200, 200, 200))
                                .selection(SplitFlap.TIME_0_TO_5)
                                .build();

        flip6 = SplitFlapBuilder.create()
                                .prefWidth(80)
                                .prefHeight(133)
                                .flipTimeInMs(350)
                                .color(Color.rgb(220, 20, 0))
                                .textColor(Color.rgb(200, 200, 200))
                                .selection(SplitFlap.TIME_0_TO_9)
                                .build();

        // Layout
        final GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(0);
        pane.setVgap(5);
        pane.setAlignment(Pos.TOP_CENTER);

        // Add controls to the layout
        pane.add(flip1, 1, 1);
        pane.add(flip2, 2, 1);
        GridPane.setMargin(flip2, new Insets(0, 10, 0, 0));
        pane.add(flip3, 6, 1);
        pane.add(flip4, 7, 1);
        GridPane.setMargin(flip4, new Insets(0, 10, 0, 0));
        pane.add(flip5, 11, 1);
        pane.add(flip6, 12, 1);

        getChildren().add(pane);
    }

    private void setClock() {
        Calendar cal = Calendar.getInstance();
        int hh = cal.get(Calendar.HOUR_OF_DAY);
        int mm = cal.get(Calendar.MINUTE);
        int ss = cal.get(Calendar.SECOND);

        // Hours
        if (hh < 10) {
            flip1.setText("0");
            flip2.setText(Integer.toString(hh));
        } else {
            flip1.setText(Integer.toString(hh).substring(0, 1));
            flip2.setText(Integer.toString(hh).substring(1, 2));
        }

        // Minutes
        if (mm < 10) {
            flip3.setText("0");
            flip4.setText(Integer.toString(mm));
        } else {
            flip3.setText(Integer.toString(mm).substring(0, 1));
            flip4.setText(Integer.toString(mm).substring(1, 2));
        }

        // Seconds
        if (ss < 10) {
            flip5.setText("0");
            flip6.setText(Integer.toString(ss));
        } else {
            flip5.setText(Integer.toString(ss).substring(0, 1));
            flip6.setText(Integer.toString(ss).substring(1, 2));
        }
    }

    @Override
    public void play() {
        TIMER.start();
    }

    @Override
    public void stop() {
        TIMER.stop();
    }
}