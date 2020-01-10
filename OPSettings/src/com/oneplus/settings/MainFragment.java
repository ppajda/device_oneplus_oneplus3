/*
 * Copyright (c) 2016 The CyanogenMod Project
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

import android.os.Bundle;
import android.provider.Settings;

import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragment;
import androidx.preference.SwitchPreference;

import com.oneplus.settings.utils.Constants;

public class MainFragment extends PreferenceFragment {
    private ListPreference mSliderTop;
    private ListPreference mSliderMiddle;
    private ListPreference mSliderBottom;

    private SwitchPreference mButtonSwap;
    private SwitchPreference mGestureCamera;
    private SwitchPreference mGestureFlashlight;
    private SwitchPreference mGestureMusic;
    private SwitchPreference mHapticFeedback;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getActivity().getActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.fragment_main);

        mSliderTop = (ListPreference) findPreference("notification_slider_top");
        mSliderTop.setValue((String) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.NOTIFICATION_SLIDER_TOP_KEY));
        mSliderTop.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                String value = (String) objectValue;

                Settings.System.putString(getActivity().getContentResolver(), Constants.NOTIFICATION_SLIDER_TOP_KEY, value);

                Constants.setPreferenceToNode(Constants.NOTIFICATION_SLIDER_TOP_KEY, value);
                return true;
            }
        });

        mSliderMiddle = (ListPreference) findPreference("notification_slider_middle");
        mSliderMiddle.setValue((String) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.NOTIFICATION_SLIDER_MIDDLE_KEY));
        mSliderMiddle.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                String value = (String) objectValue;

                Settings.System.putString(getActivity().getContentResolver(), Constants.NOTIFICATION_SLIDER_MIDDLE_KEY, value);

                Constants.setPreferenceToNode(Constants.NOTIFICATION_SLIDER_MIDDLE_KEY, value);
                return true;
            }
        });

        mSliderBottom = (ListPreference) findPreference("notification_slider_bottom");
        mSliderBottom.setValue((String) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.NOTIFICATION_SLIDER_BOTTOM_KEY));
        mSliderBottom.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                String value = (String) objectValue;

                Settings.System.putString(getActivity().getContentResolver(), Constants.NOTIFICATION_SLIDER_BOTTOM_KEY, value);

                Constants.setPreferenceToNode(Constants.NOTIFICATION_SLIDER_BOTTOM_KEY, value);
                return true;
            }
        });

        mButtonSwap = (SwitchPreference) findPreference("button_swap");
        mButtonSwap.setChecked((int) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.BUTTON_SWAP_KEY) == 1);
        mButtonSwap.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                int value = (boolean) objectValue ? 1 : 0;

                Settings.System.putInt(getActivity().getContentResolver(), Constants.BUTTON_SWAP_KEY, value);

                Constants.setPreferenceToNode(Constants.BUTTON_SWAP_KEY, Integer.toString(value));
                return true;
            }
        });

        mGestureCamera = (SwitchPreference) findPreference("touchscreen_gesture_camera");
        mGestureCamera.setChecked((int) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.TOUCHSCREEN_GESTURE_CAMERA_KEY) == 1);
        mGestureCamera.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                int value = (boolean) objectValue ? 1 : 0;

                Settings.System.putInt(getActivity().getContentResolver(), Constants.TOUCHSCREEN_GESTURE_CAMERA_KEY, value);

                Constants.setPreferenceToNode(Constants.TOUCHSCREEN_GESTURE_CAMERA_KEY, Integer.toString(value));
                return true;
            }
        });

        mGestureFlashlight = (SwitchPreference) findPreference("touchscreen_gesture_flashlight");
        mGestureFlashlight.setChecked((int) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY) == 1);
        mGestureFlashlight.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                int value = (boolean) objectValue ? 1 : 0;

                Settings.System.putInt(getActivity().getContentResolver(), Constants.TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY, value);

                Constants.setPreferenceToNode(Constants.TOUCHSCREEN_GESTURE_FLASHLIGHT_KEY, Integer.toString(value));
                return true;
            }
        });

        mGestureMusic = (SwitchPreference) findPreference("touchscreen_gesture_music");
        mGestureMusic.setChecked((int) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.TOUCHSCREEN_GESTURE_MUSIC_KEY) == 1);
        mGestureMusic.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                int value = (boolean) objectValue ? 1 : 0;

                Settings.System.putInt(getActivity().getContentResolver(), Constants.TOUCHSCREEN_GESTURE_MUSIC_KEY, value);

                for (String musicNode : Constants.TOUCHSCREEN_GESTURE_MUSIC_NODES) {
                    Constants.setPreferenceToNode(musicNode, Integer.toString(value));
                }
                return true;
            }
        });

        mHapticFeedback = (SwitchPreference) findPreference("touchscreen_gesture_haptic_feedback");
        mHapticFeedback.setChecked((int) Constants.getPreferenceValue(getActivity().getApplicationContext(), Constants.TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY) == 1);
        mHapticFeedback.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object objectValue) {
                int value = (boolean) objectValue ? 1 : 0;

                Settings.System.putInt(getActivity().getContentResolver(), Constants.TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY, value);

                // Node for TOUCHSCREEN_GESTURE_HAPTIC_FEEDBACK_KEY does not exist
                return true;
            }
        });
    }
}
