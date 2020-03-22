/*
* Copyright (C) 2013 The OmniROM Project
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation, either version 2 of the License, or
* (at your option) any later version.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*
*/
package org.omnirom.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import android.provider.Settings;
import android.text.TextUtils;

import org.omnirom.device.kcal.KUtils;

public class Startup extends BroadcastReceiver implements KUtils {

    private void restore(String file, boolean enabled) {
        if (file == null) {
            return;
        }
        if (enabled) {
            Utils.writeValue(file, "1");
        }
    }

    private void restore(String file, String value) {
        if (file == null) {
            return;
        }
        Utils.writeValue(file, value);
    }

    private String getGestureFile(String key) {
        return GestureSettings.getGestureFile(key);
    }

    @Override
    public void onReceive(final Context context, final Intent bootintent) {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        // double swipe -> music play
        String mapping = GestureSettings.DEVICE_GESTURE_MAPPING_0;
        String value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.MUSIC_PLAY_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        boolean enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_DOUBLE_SWIPE_APP), enabled);

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_FPS_INFO, false);
        if (enabled) {
            context.startService(new Intent(context, FPSInfoService.class));
        }

	if (Settings.Secure.getInt(context.getContentResolver(), PREF_ENABLED, 0) == 1) {
            Utils.setValue(KCAL_ENABLE, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_ENABLED, 0));

            String rgbValue = Settings.Secure.getInt(context.getContentResolver(),
                    PREF_RED, RED_DEFAULT) + " " +
                    Settings.Secure.getInt(context.getContentResolver(), PREF_GREEN,
                            GREEN_DEFAULT) + " " +
                    Settings.Secure.getInt(context.getContentResolver(), PREF_BLUE,
                            BLUE_DEFAULT);

            Utils.setValue(KCAL_RGB, rgbValue);
            Utils.setValue(KCAL_MIN, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_MINIMUM, MINIMUM_DEFAULT));
            Utils.setValue(KCAL_SAT, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_GRAYSCALE, 0) == 1 ? 128 :
                    Settings.Secure.getInt(context.getContentResolver(),
                            PREF_SATURATION, SATURATION_DEFAULT) + SATURATION_OFFSET);
            Utils.setValue(KCAL_VAL, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_VALUE, VALUE_DEFAULT) + VALUE_OFFSET);
            Utils.setValue(KCAL_CONT, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_CONTRAST, CONTRAST_DEFAULT) + CONTRAST_OFFSET);
            Utils.setValue(KCAL_HUE, Settings.Secure.getInt(context.getContentResolver(),
                    PREF_HUE, HUE_DEFAULT));
        }

        // circle -> camera
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_1;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.CAMERA_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_CIRCLE_APP), enabled);

        // down arrow -> flashlight
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_2;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.TORCH_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_DOWN_ARROW_APP), enabled);

        // up arrow
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_3);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_UP_ARROW_APP), enabled);

        // left arrow -> music prev
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_4;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.MUSIC_PREV_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_LEFT_ARROW_APP), enabled);

        // right arrow -> music next
        mapping = GestureSettings.DEVICE_GESTURE_MAPPING_5;
        value = Settings.System.getString(context.getContentResolver(), mapping);
        if (TextUtils.isEmpty(value)) {
            value = AppSelectListPreference.MUSIC_NEXT_ENTRY;
            Settings.System.putString(context.getContentResolver(), mapping, value);
        }
        enabled = !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_RIGHT_ARROW_APP), enabled);

        // down swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_6);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_DOWN_SWIPE_APP), enabled);

        // up swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_7);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_UP_SWIPE_APP), enabled);

        // left swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_8);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_LEFT_SWIPE_APP), enabled);

        // right swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_9);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.KEY_RIGHT_SWIPE_APP), enabled);

        // fp down swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_10);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.FP_GESTURE_SWIPE_DOWN_APP), enabled);

        // fp up swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_11);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.FP_GESTURE_SWIPE_UP_APP), enabled);

        // fp right swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_12);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.FP_GESTURE_SWIPE_RIGHT_APP), enabled);

        // fp left swipe
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_13);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.FP_GESTURE_SWIPE_LEFT_APP), enabled);

        // fp long press
        value = Settings.System.getString(context.getContentResolver(), GestureSettings.DEVICE_GESTURE_MAPPING_14);
        enabled = !TextUtils.isEmpty(value) && !value.equals(AppSelectListPreference.DISABLED_ENTRY);
        restore(getGestureFile(GestureSettings.FP_GESTURE_LONG_PRESS_APP), enabled);

        sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_SRGB_SWITCH, false);
        restore(SRGBModeSwitch.getFile(), enabled);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_HBM_SWITCH, false);
        restore(HBMModeSwitch.getFile(), enabled);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_DCI_SWITCH, false);
        restore(DCIModeSwitch.getFile(), enabled);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_NIGHT_SWITCH, false);
        restore(NightModeSwitch.getFile(), enabled);
        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_ONEPLUS_SWITCH, false);
        restore(OnePlusModeSwitch.getFile(), enabled);

        enabled = sharedPrefs.getBoolean(DeviceSettings.KEY_HWK_SWITCH, false);
        restore(HWKSwitch.getFile(), enabled);

        VibratorStrengthPreference.restore(context);
    }
}
