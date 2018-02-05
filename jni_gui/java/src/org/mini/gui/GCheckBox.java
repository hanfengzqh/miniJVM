/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mini.gui;

import static org.mini.glfw.utils.Gutil.toUtf8;
import org.mini.glfw.utils.Nutil;
import static org.mini.glfw.utils.Nutil.NVG_ALIGN_CENTER;
import static org.mini.glfw.utils.Nutil.NVG_ALIGN_LEFT;
import static org.mini.glfw.utils.Nutil.NVG_ALIGN_MIDDLE;
import static org.mini.glfw.utils.Nutil.nvgBeginPath;
import static org.mini.glfw.utils.Nutil.nvgBoxGradient;
import static org.mini.glfw.utils.Nutil.nvgFill;
import static org.mini.glfw.utils.Nutil.nvgFillColor;
import static org.mini.glfw.utils.Nutil.nvgFillPaint;
import static org.mini.glfw.utils.Nutil.nvgFontFace;
import static org.mini.glfw.utils.Nutil.nvgFontSize;
import static org.mini.glfw.utils.Nutil.nvgRoundedRect;
import static org.mini.glfw.utils.Nutil.nvgTextAlign;
import static org.mini.gui.GToolkit.nvgRGBA;

/**
 *
 * @author gust
 */
public class GCheckBox extends GObject {

    String text;
    byte[] text_arr;
    boolean checked;

    public GCheckBox(String text, boolean checked, int left, int top, int width, int height) {
        setText(text);
        this.checked = checked;
        boundle[LEFT] = left;
        boundle[TOP] = top;
        boundle[WIDTH] = width;
        boundle[HEIGHT] = height;
    }

    public void setText(String text) {
        this.text = text;
        text_arr = toUtf8(text);
    }

    @Override
    public void mouseButtonEvent(int button, boolean pressed, int x, int y) {
        if (isInBoundle(boundle, x - parent.getX(), y - parent.getY())) {
            if (pressed) {
            } else {
                checked = !checked;
                parent.setFocus(this);
                if (stateListener != null) {
                    stateListener.stateChange();
                }
            }
        }
    }

    /**
     *
     * @param vg
     * @return
     */
    public boolean update(long vg) {
        float x = getX();
        float y = getY();
        float w = getW();
        float h = getH();

        byte[] bg;

        //NVG_NOTUSED(w);
        nvgFontSize(vg, textFontSize);
        nvgFontFace(vg, GToolkit.getFontWord());
        nvgFillColor(vg, nvgRGBA(255, 255, 255, 160));

        nvgTextAlign(vg, NVG_ALIGN_LEFT | NVG_ALIGN_MIDDLE);
        Nutil.nvgTextJni(vg, x + 28, y + h * 0.5f, text_arr, 0, text_arr.length);

        bg = nvgBoxGradient(vg, x + 1, y + (int) (h * 0.5f) - 9 + 1, 18, 18, 3, 3, nvgRGBA(0, 0, 0, 32), nvgRGBA(0, 0, 0, 92));
        nvgBeginPath(vg);
        nvgRoundedRect(vg, x + 1, y + (int) (h * 0.5f) - 9, 18, 18, 3);
        nvgFillPaint(vg, bg);
        nvgFill(vg);

        nvgFontSize(vg, 40);
        nvgFontFace(vg, GToolkit.getFontIcon());
        nvgFillColor(vg, nvgRGBA(255, 255, 255, 128));
        nvgTextAlign(vg, NVG_ALIGN_CENTER | NVG_ALIGN_MIDDLE);
        byte[] barr = toUtf8("" + (checked ? ICON_CHECK : ICON_CHECK_NOT));
        Nutil.nvgTextJni(vg, x + 9 + 2, y + h * 0.5f, barr, 0, barr.length);
        return true;
    }

    /**
     * @return the checked
     */
    public boolean isChecked() {
        return checked;
    }

    /**
     * @param checked the checked to set
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
