package org.bitm.pencilbox.userloginpb5;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Mobile App on 5/16/2018.
 */

public class EmployeeAdapter extends ArrayAdapter<BaseSalariedEmployee> {

    private Context context;
    private List<BaseSalariedEmployee>bse;

    public EmployeeAdapter(@NonNull Context context, List<BaseSalariedEmployee>bse) {
        super(context, R.layout.employee_row,bse);
        this.context = context;
        this.bse = bse;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.employee_row,parent,false);
        TextView nameTV = convertView.findViewById(R.id.row_empName);
        TextView desgTV = convertView.findViewById(R.id.row_empDesg);

        nameTV.setText(bse.get(position).getEmpName());
        desgTV.setText(bse.get(position).getEmpDesg());
        return convertView;
    }
}
