/*
 * Copyright (c) 2015 The CyanogenMod Project
 * Copyright (c) 2017 The LineageOS Project
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

package com.oneplus.settings;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.util.Log;

import com.oneplus.settings.utils.Constants;

import java.io.File;

public class BootCompletedReceiver extends BroadcastReceiver {
    public String TAG = "OPSettings-BootCompletedReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_BOOT_COMPLETED.equals(intent.getAction()) || Intent.ACTION_PRE_BOOT_COMPLETED.equals(intent.getAction())) {
            if (!Constants.BOOT_COMPLETED) {
                Log.i(TAG, "Setting/writing preference values to nodes");

                if (hasButtons()) {
                    enableComponent(context, MainActivity.class.getName());

                    for (String preference : Constants.sButtonKeys) {
                        Object objectValue = Constants.getPreferenceValue(context, preference);

                        if (objectValue instanceof Integer) {
                            String value = Integer.toString((int) objectValue);

                            Constants.setPreferenceToNode(preference, value);
                        } else if (objectValue instanceof String) {
                            String value = (String) objectValue;

                            Constants.setPreferenceToNode(preference, value);
                        }
                    }
                } else {
                    disableComponent(context, MainActivity.class.getName());
                }

                if (hasGestures()) {
                    enableComponent(context, MainActivity.class.getName());

                    for (String preference : Constants.sGestureKeys) {
                        if (!preference.equals(Constants.TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY)) { // Node for TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY does not exist
                            Object objectValue = Constants.getPreferenceValue(context, preference);

                            if (objectValue instanceof Integer) {
                                String value = Integer.toString((int) objectValue);

                                if (preference.equals(Constants.TOUCHSCREEN_GESTURE_MUSIC_KEY)) {
                                    for (String musicNode : Constants.TOUCHSCREEN_GESTURE_MUSIC_NODES) {
                                        Constants.setPreferenceToNode(musicNode, value);
                                    }

                                    continue;
                                }

                                Constants.setPreferenceToNode(preference, value);
                            }
                        }
                    }
                } else {
                    disableComponent(context, MainActivity.class.getName());
                }

                Constants.BOOT_COMPLETED = true;
            }
        }
    }

    private boolean hasButtons () {
        return new File(Constants.BUTTON_SWAP_NODE).exists() ||
              (new File(Constants.NOTIFICATION_SLIDER_TOP_NODE).exists() &&
               new File(Constants.NOTIFICATION_SLIDER_MIDDLE_NODE).exists() &&
               new File(Constants.NOTIFICATION_SLIDER_BOTTOM_NODE).exists());
    }

    private boolean hasGestures () {
        return new File(Constants.TOUCHSCREEN_GESTURE_CAMERA_NODE).exists() &&
               new File(Constants.TOUCHSCREEN_GESTURE_FLASHLIGHT_NODE).exists() &&
               new File(Constants.TOUCHSCREEN_GESTURE_DOUBLE_SWIPE_NODE).exists();
    }

    private void disableComponent(Context context, String component) {
        ComponentName componentName = new ComponentName(context, component);
        PackageManager packageManager = context.getPackageManager();
        packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }

    private void enableComponent(Context context, String component) {
        ComponentName componentName = new ComponentName(context, component);
        PackageManager packageManager = context.getPackageManager();
        if (packageManager.getComponentEnabledSetting(componentName) == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            packageManager.setComponentEnabledSetting(componentName, PackageManager.COMPONENT_ENABLED_STATE_ENABLED, PackageManager.DONT_KILL_APP);
        }
    }
}
