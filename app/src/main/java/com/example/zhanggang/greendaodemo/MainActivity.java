package com.example.zhanggang.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.wxample.zhanggang.greendao.gen.UserDao;

import org.greenrobot.greendao.annotation.Id;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_sex)
    EditText edSex;
    @BindView(R.id.charu)
    Button charu;
    @BindView(R.id.shanchu)
    Button shanchu;
    @BindView(R.id.xiugai)
    Button xiugai;
    @BindView(R.id.listview)
    ListView listview;
    @BindView(R.id.ed_newname)
    EditText edNewname;
    @BindView(R.id.ed_newsex)
    EditText edNewsex;
    private MyAdapter adapter;
    private List<User> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();


    }

    private void initData() {
        //获得集合
        list = GreenDaoManager.getInstance().getDaoSession().getUserDao().queryBuilder().list();
        adapter = new MyAdapter(this, list);
        listview.setAdapter(adapter);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.charu: //插入数据
                insert();
                break;
            case R.id.shanchu:  //删除数据
                delete();
                break;
            case R.id.xiugai:   //修改数据
                update();
                break;
        }
    }

    //修改方法
    private void update() {
        UserDao userDao2 = GreenDaoManager.getInstance().getDaoSession().getUserDao();
        String oldname = edName.getText().toString();  //被修改的姓名
        String newname = edNewname.getText().toString(); //修改后的姓名
        String newsex = edNewsex.getText().toString();  //修改后的性别
        //得到集合
        List<User> userList = userDao2.queryBuilder().where(UserDao.Properties.Name.eq(oldname)).build().list();
        if (userList != null) {
            for (User bean : userList) {  //遍历集合 赋值 修改数据
                bean.setName(newname);
                bean.setSex(newsex);
                userDao2.update(bean);
            }
            adapter.notifyDataSetChanged();
            edName.setText("");
            edSex.setText("");
            edNewname.setText("");
            edNewsex.setText("");
            Toast.makeText(this, "修改成功！", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "修改失败！", Toast.LENGTH_SHORT).show();
        }
    }

    //删除方法
    private void delete() {
        if (edName.getText().toString().equals("")) {
            Toast.makeText(this, "查无此人！", Toast.LENGTH_SHORT).show();
        }
        String name1 = edName.getText().toString();
        UserDao userDao1 = GreenDaoManager.getInstance().getDaoSession().getUserDao();

        List<User> userlist = userDao1.queryBuilder().where(UserDao.Properties.Name.eq(name1)).build().list();
        if (userlist.size() != 0) {
            for (User bean : userlist) {
                userDao1.deleteByKey(bean.getId());
                list.remove(bean);
                Toast.makeText(this, "删除成功！", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "查无此人！", Toast.LENGTH_SHORT).show();
        }
        edName.setText("");
        adapter.notifyDataSetChanged();
    }

    //插入方法
    private void insert() {
        //获取输入框内容 创建对象 放入集合中
        String name = edName.getText().toString();
        String sex = edSex.getText().toString();
        if (name.equals("") || sex.equals("")) {
            Toast.makeText(this, "请输入信息", Toast.LENGTH_SHORT).show();
        } else {
            UserDao userDao = GreenDaoManager.getInstance().getDaoSession().getUserDao();
            User user = new User(null, name, sex);
            userDao.insert(user);
            edName.setText("");
            edSex.setText("");
            //清空集合  重新查询集合再放入集合中  刷新适配器
            list.clear();
            list.addAll(userDao.queryBuilder().list());
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "插入成功！", Toast.LENGTH_SHORT).show();
        }
    }
}
