package com.oc.ichaithat.rowselectorview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import java.util.ArrayList;

/**
 * Created by iChaithat on 9/09/2016.
 */
public class AnimationSelectorView extends RelativeLayout {

    View _vRoot;
    ListView _vList;
    Boolean _isSelected;

    public AnimationSelectorView(Context context) {
        super(context);
        init(context);
    }

    public AnimationSelectorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AnimationSelectorView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    public void init(Context aContext) {
        _vRoot = inflate(aContext, R.layout.animation_selector_layout, this);

        _isSelected = false;

        final ArrayList<String> items = new ArrayList<>();
        items.add("testing1");
        items.add("testing2");
        items.add("testing3");
        items.add("testing4");
        items.add("testing5");

        final ArrayList<String> using = new ArrayList<>();
        using.addAll(items);

        final AnimationSelectorAdapter adapter = new AnimationSelectorAdapter(aContext, using);

        _vList = (ListView) _vRoot.findViewById(R.id.vList);
        _vList.setAdapter(adapter);
        _vList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                _isSelected = !_isSelected;
                if (_isSelected) {
                    final int position = i;

                    for (int index = items.size() - 1; index >= 0; index--) {
                        final int current = index;
                        Animation animation = new ScaleAnimation(1, 1, 1, 0);
                        animation.setDuration(200);
                        animation.setStartOffset(200 * current);
                        _vList.getChildAt(index).startAnimation(animation);
                        adapterView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                using.remove(using.size() - 1);
                                if (using.size() == 0) {
                                    using.add(items.get(position));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }, 200 * current);
                    }
                }
                else  {
                    for (int index = 0; index < items.size(); index++) {
                        final int current = index;
                        adapterView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (current == 0) {
                                    using.set(0, items.get(0));
                                }
                                else  {
                                    using.add(items.get(current));
                                }
                                adapter.notifyDataSetChanged();
                            }
                        }, 200 * current);
                    }
                }
            }
        });
    }
}
