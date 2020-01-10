/*
 * Copyright (C) 2015 The CyanogenMod Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.oneplus.settings.utils;

import android.content.Context;
import android.provider.Settings;
import android.util.Log;
import android.util.SparseIntArray;

import java.util.ArrayList;
import java.util.HashMap;

public class Constants {
    public static String TAG = "OPSettings-Constants";

    public static boolean BOOT_COMPLETED = false;

    public static int ZEN_MODE_VIBRATION = 4;

    // Keycodes
    public static final int KEY_DOUBLE_TAP = 249;
    public static final int KEY_GESTURE_CIRCLE = 250;
    public static final int KEY_GESTURE_TWO_SWIPE = 251;
    public static final int KEY_GESTURE_DOWN_ARROW = 252;
    public static final int KEY_GESTURE_LEFT_V = 253;
    public static final int KEY_GESTURE_RIGHT_V = 254;

    public static final int KEY_SLIDER_MODE_TOTAL_SILENCE = 600;
    public static final int KEY_SLIDER_MODE_PRIORITY_ONLY = 601;
    public static final int KEY_SLIDER_MODE_VIBRATION = 602;
    public static final int KEY_SLIDER_MODE_NONE = 603;

    public static ArrayList<Integer> sSupportedGestures = new ArrayList<>();
    static {
        sSupportedGestures.add(KEY_DOUBLE_TAP);
        sSupportedGestures.add(KEY_GESTURE_CIRCLE);
        sSupportedGestures.add(KEY_GESTURE_TWO_SWIPE);
        sSupportedGestures.add(KEY_GESTURE_DOWN_ARROW);
        sSupportedGestures.add(KEY_GESTURE_LEFT_V);
        sSupportedGestures.add(KEY_GESTURE_RIGHT_V);
    }

    public static SparseIntArray sSupportedSliderModes = new SparseIntArray();
    static {
        sSupportedSliderModes.put(KEY_SLIDER_MODE_TOTAL_SILENCE, Settings.Global.ZEN_MODE_NO_INTERRUPTIONS);
        sSupportedSliderModes.put(KEY_SLIDER_MODE_PRIORITY_ONLY, Settings.Global.ZEN_MODE_IMPORTANT_INTERRUPTIONS);
        sSupportedSliderModes.put(KEY_SLIDER_MODE_VIBRATION, ZEN_MODE_VIBRATION);
        sSupportedSliderModes.put(KEY_SLIDER_MODE_NONE, Settings.Global.ZEN_MODE_OFF);
    }

    // Button swap
    public static String BUTTON_SWAP_KEY = "button_swap";

    // Button swap nodes
    public static String BUTTON_SWAP_NODE = "/proc/s1302/key_rep";

    // Notification slider
    public static String NOTIFICATION_SLIDER_TOP_KEY = "notification_slider_top";
    public static String NOTIFICATION_SLIDER_MIDDLE_KEY = "notification_slider_middle";
    public static String NOTIFICATION_SLIDER_BOTTOM_KEY = "notification_slider_bottom";

    // Notification slider nodes
    public static String NOTIFICATION_SLIDER_TOP_NODE = "/proc/tri-state-key/keyCode_top";
    public static String NOTIFICATION_SLIDER_MIDDLE_NODE = "/proc/tri-state-key/keyCode_middle";
    public static String NOTIFICATION_SLIDER_BOTTOM_NODE = "/proc/tri-state-key/keyCode_bottom";

    // Touchscreen gestures
    public static String TOUCHSCREEN_GESTURE_CAMERA_KEY = "touchscreen_gesture_camera";
    public static String TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY = "touchscreen_gesture_flashlight";
    public static String TOUCHSCREEN_GESTURE_MUSIC_KEY = "touchscreen_gesture_music";
	public static String TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY = "touchscreen_gesture_haptic_feedback";

    // Touchscreen gestures nodes
    public static String TOUCHSCREEN_GESTURE_CAMERA_NODE = "/proc/touchpanel/letter_o_enable";
    public static String TOUCHSCREEN_GESTURE_FLASHLIGHT_NODE = "/proc/touchpanel/down_arrow_enable";
    public static String TOUCHSCREEN_GESTURE_DOUBLE_SWIPE_NODE = "/proc/touchpanel/double_swipe_enable";
    public static String TOUCHSCREEN_GESTURE_LEFT_ARROW_NODE = "/proc/touchpanel/left_arrow_enable";
    public static String TOUCHSCREEN_GESTURE_RIGHT_ARROW_NODE = "/proc/touchpanel/right_arrow_enable";
    public static String[] TOUCHSCREEN_GESTURE_MUSIC_NODES = {TOUCHSCREEN_GESTURE_DOUBLE_SWIPE_NODE, TOUCHSCREEN_GESTURE_LEFT_ARROW_NODE, TOUCHSCREEN_GESTURE_RIGHT_ARROW_NODE};

    // <PREFERENCE_KEY> -> <PREFERENCE_NODE> mapping
    public static HashMap<String, String> sNodePreferenceMap = new HashMap<>();
    static {
        sNodePreferenceMap.put(BUTTON_SWAP_KEY, BUTTON_SWAP_NODE);
        sNodePreferenceMap.put(NOTIFICATION_SLIDER_TOP_KEY, NOTIFICATION_SLIDER_TOP_NODE);
        sNodePreferenceMap.put(NOTIFICATION_SLIDER_MIDDLE_KEY, NOTIFICATION_SLIDER_MIDDLE_NODE);
        sNodePreferenceMap.put(NOTIFICATION_SLIDER_BOTTOM_KEY, NOTIFICATION_SLIDER_BOTTOM_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_GESTURE_CAMERA_KEY, TOUCHSCREEN_GESTURE_CAMERA_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY, TOUCHSCREEN_GESTURE_FLASHLIGHT_NODE);
        sNodePreferenceMap.put(TOUCHSCREEN_GESTURE_MUSIC_KEY, TOUCHSCREEN_GESTURE_DOUBLE_SWIPE_NODE);
    }

    // <PREFERENCE_KEY> -> <DEFAULT_VALUE> mapping
    public static HashMap<String, Object> sNodeDefaultMap = new HashMap<>();
    static {
        sNodeDefaultMap.put(BUTTON_SWAP_KEY, 0);
        sNodeDefaultMap.put(NOTIFICATION_SLIDER_TOP_KEY, "601");
        sNodeDefaultMap.put(NOTIFICATION_SLIDER_MIDDLE_KEY, "602");
        sNodeDefaultMap.put(NOTIFICATION_SLIDER_BOTTOM_KEY, "603");
        sNodeDefaultMap.put(TOUCHSCREEN_GESTURE_CAMERA_KEY, 1);
        sNodeDefaultMap.put(TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY, 1);
        sNodeDefaultMap.put(TOUCHSCREEN_GESTURE_MUSIC_KEY, 1);
        sNodeDefaultMap.put(TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY, 1);
    }

    public static ArrayList<String> sButtonKeys = new ArrayList<>();
    static {
        sButtonKeys.add(BUTTON_SWAP_KEY);
        sButtonKeys.add(NOTIFICATION_SLIDER_TOP_KEY);
        sButtonKeys.add(NOTIFICATION_SLIDER_MIDDLE_KEY);
        sButtonKeys.add(NOTIFICATION_SLIDER_BOTTOM_KEY);
    }

    public static ArrayList<String> sGestureKeys = new ArrayList<>();
    static {
        sGestureKeys.add(TOUCHSCREEN_GESTURE_CAMERA_KEY);
        sGestureKeys.add(TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY);
        sGestureKeys.add(TOUCHSCREEN_GESTURE_MUSIC_KEY);
        sGestureKeys.add(TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY);
    }

    public static Object getPreferenceValue(Context context, String preference) {
        if (!preference.equals(NOTIFICATION_SLIDER_TOP_KEY) && !preference.equals(NOTIFICATION_SLIDER_MIDDLE_KEY) && !preference.equals(NOTIFICATION_SLIDER_BOTTOM_KEY)) {
            int value = Settings.System.getInt(context.getContentResolver(), preference, -1);
            if (value == -1) {
                value = (int) sNodeDefaultMap.get(preference);
            }

            return value;
        } else {
            String value = Settings.System.getString(context.getContentResolver(), preference);
            if (value == null) {
                value = (String) sNodeDefaultMap.get(preference);
            }

            return value;
        }
    }

    public static void setPreferenceToNode(String preference, String value) {
        String node;
        if (!preference.equals(TOUCHSCREEN_GESTURE_DOUBLE_SWIPE_NODE) && !preference.equals(TOUCHSCREEN_GESTURE_LEFT_ARROW_NODE) && !preference.equals(TOUCHSCREEN_GESTURE_RIGHT_ARROW_NODE)) {
            node = sNodePreferenceMap.get(preference);
        } else {
            node = preference;
        }

        if (!FileUtils.writeLine(node, value)) {
            Log.w(TAG, "Write " + value + " to node " + node + " failed");
        }
    }
}
