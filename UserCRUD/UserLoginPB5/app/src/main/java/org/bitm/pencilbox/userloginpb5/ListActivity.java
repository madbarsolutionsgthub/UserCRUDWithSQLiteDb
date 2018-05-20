package org.bitm.pencilbox.userloginpb5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.bitm.pencilbox.userloginpb5.Database.EmployeeDataSource;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private ListView listView;
    private List<BaseSalariedEmployee>bse = new ArrayList<>();
    private EmployeeAdapter adapter;
    private EmployeeDataSource source;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.listView);
        source = new EmployeeDataSource(this);
        bse = source.getAllEmployees();
        adapter = new EmployeeAdapter(this,bse);
        listView.setAdapter(adapter);
        registerForContextMenu(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                /*startActivity(new Intent(ListActivity.this,DetailsActivity.class)
                .putExtra("emp",position));*/
            }
        });
    }

    public void addEmployee(View view) {
        startActivity(new Intent(this,HomeActivity.class));
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.emp_list_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;

        switch (item.getItemId()){
            case R.id.edit:
                int empId = bse.get(position).getEmpId();
                startActivity(new Intent(ListActivity.this,HomeActivity.class).putExtra("empId",empId));
                break;
            case R.id.delete:
                int rowId = bse.get(position).getEmpId();
                boolean status = source.deleteEmployee(rowId);
                if(status){
                    bse.clear();
                    bse = source.getAllEmployees();
                    adapter = new EmployeeAdapter(this,bse);
                    listView.setAdapter(adapter);
                }else{
                    Toast.makeText(ListActivity.this, "could not delete", Toast.LENGTH_SHORT).show();
                }
                break;
        }
        return super.onContextItemSelected(item);
    }
}
