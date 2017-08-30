package com.oc.ichaithat.rowselectorview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by iChaithat on 9/09/2016.
 */
public class AnimationSelectorAdapter extends BaseAdapter {
    private Context _context;
    private LayoutInflater _layout;
    private ArrayList<String> _arItems;

    public AnimationSelectorAdapter(Context aContext, ArrayList<String> aItems) {
        super();

        _context = aContext;
        _arItems = aItems;
        _layout = (LayoutInflater) _context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //NumberOfRow
    @Override
    public int getCount() {
        return _arItems.size();
    }

    //Get item at index
    @Override
    public Object getItem(int position) {
        return _arItems.get(position);
    }

    //Get position at id
    @Override
    public long getItemId(int position) {
        return position;
    }

    //CellForRow
    @Override
    public View getView(int aPosition, View aConvertView, ViewGroup aParent) {
        ViewHolder vHolder;
        if (aConvertView == null) {
            vHolder = new ViewHolder();
            aConvertView = _layout.inflate(R.layout.cell_selector_layout, aParent, false);
            vHolder.tvTitle = (TextView)aConvertView.findViewById(R.id.tvTitle);

            aConvertView.setTag(vHolder);
        }
        else  {
            vHolder = (ViewHolder)aConvertView.getTag();
        }

        String title = (String) getItem(aPosition);
        vHolder.tvTitle.setText(title);

        return aConvertView;
    }

    private static class ViewHolder {
        public TextView tvTitle;
    }
}
