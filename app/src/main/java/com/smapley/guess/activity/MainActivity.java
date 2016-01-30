package com.smapley.guess.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.smapley.guess.R;
import com.smapley.guess.adapter.MainAdapter;
import com.smapley.guess.http.params.GetJilu1Params;
import com.smapley.guess.http.params.TuimaG3Params;
import com.smapley.guess.http.params.UpdateZt1Params;
import com.smapley.guess.http.params.XiaZhuParams;
import com.smapley.guess.http.service.Getjilu1Service;
import com.smapley.guess.http.service.TuimaG3Service;
import com.smapley.guess.http.service.UpdateZt1Service;
import com.smapley.guess.http.service.XiaZhuService;
import com.smapley.guess.utils.MyData;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ContentView(R.layout.activity_main)
public class MainActivity extends BaseActivity {

    @ViewInject(R.id.layout)
    private View layout;

    @ViewInject(R.id.listview)
    private ListView listView;

    @ViewInject(R.id.num)
    private TextView num;
    @ViewInject(R.id.num_ico)
    private TextView num_ico;
    @ViewInject(R.id.gold)
    private TextView gold;
    @ViewInject(R.id.gold_ico)
    private TextView gold_ico;
    @ViewInject(R.id.type)
    private TextView type;


    //当前选择的输入框
    private View textLayout;
    private TextView text;
    private View textIco;

    //是否有小数点
    private boolean hasPoint = false;
    //是否第一次选择金额
    private boolean jine = false;

    //倒状态
    private boolean dao1 = false;
    private boolean dao2 = false;
    private boolean dao3 = false;
    private boolean dao4 = false;

    //千百十个状态
    private boolean qian1 = false;
    private boolean bai1 = false;
    private boolean shi1 = false;
    private boolean ge1 = false;
    private boolean qian2 = false;
    private boolean bai2 = false;
    private boolean shi2 = false;
    private boolean ge2 = false;
    private boolean qian3 = false;
    private boolean bai3 = false;
    private boolean shi3 = false;
    private boolean ge3 = false;

    public static List<Map<String, String>> removeList = new ArrayList<>();
    public static List<Map<String, String>> dataList = new ArrayList<>();

    private MainAdapter adapter;

    private String allid;


    private Getjilu1Service getjilu1Service = new Getjilu1Service() {
        @Override
        public void Succ(String data) {
            Map map1 = JSON.parseObject(data, new TypeReference<Map>() {
            });
            if (Integer.parseInt(map1.get("count").toString()) > 0) {
                allid = map1.get("allid").toString();
//                qishu = map1.get("qishu").toString();
//                yyed = map1.get("yyed1").toString();
//                tv_title2.setText(title + yyed);
                List<Map<String, String>> list = JSON.parseObject(map1.get("result").toString(), new TypeReference<List<Map<String, String>>>() {
                });
                dataList.clear();
                dataList.addAll(list);
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(adapter.getCount() - 1);
            } else if (Integer.parseInt(map1.get("count").toString()) == 0) {
                dataList.clear();
                adapter.notifyDataSetChanged();
                try {
                    allid = map1.get("allid").toString();
                } catch (Exception e) {

                }
//                yyed = map1.get("yyed1").toString();
//                tv_title2.setText(title + yyed);
//                qishu = map1.get("qishu").toString();
            }
        }
    };

    private XiaZhuService xiaZhuService = new XiaZhuService() {
        @Override
        public void Succ(String data) {
            Map map = JSON.parseObject(data, new TypeReference<Map>() {
            });
            //获取数据
            getjilu1Service.load(new GetJilu1Params(MyData.user1));
            if (Integer.parseInt(map.get("count").toString()) > 0) {
                List<Map> list = JSON.parseObject(map.get("result").toString(), new TypeReference<List<Map>>() {
                });
                for (int i = 0; i < list.size(); i++) {
                    Map resultmap = list.get(i);
                    Map dataMap = new HashMap();
                    dataMap.put("count", map.get("count").toString());
                    dataMap.put("allgold", map.get("allgold").toString());
                    dataMap.put("allid", map.get("allid").toString());
                    dataMap.put("number", resultmap.get("number").toString());
                    dataMap.put("gold", resultmap.get("gold").toString());
                    dataMap.put("pei", resultmap.get("pei").toString());
                    dataMap.put("id", resultmap.get("id").toString());
                    dataMap.put("hotstat", "0");
                    dataList.add(dataMap);
                }
                adapter.notifyDataSetChanged();
                listView.smoothScrollToPosition(adapter.getCount() - 1);


            }
            List<Map<String, String>> list1 = JSON.parseObject(map.get("disresult").toString(), new TypeReference<List<Map<String, String>>>() {
            });
            String result = "";
            for (int i = 0; i < list1.size(); i++) {
                result = result + list1.get(i).get("number").toString() + "\n";
            }
            if (result != null && !result.equals("")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("下注失败的号码：").setMessage(result)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }


        }
    };

    private UpdateZt1Service updateZt1Service = new UpdateZt1Service() {
        @Override
        public void Succ(String data) {
            Map resultmap = JSON.parseObject(data, new TypeReference<Map>() {
            });
            if (Integer.parseInt(resultmap.get("newid").toString()) > 0) {
                //获取数据
                getjilu1Service.load(new GetJilu1Params(MyData.user1));
            }
        }
    };

    private TuimaG3Service tuimaG3Service = new TuimaG3Service() {
        @Override
        public void Succ(String datas) {
            removeList.clear();
            String result2 = JSON.parseObject(datas, new TypeReference<String>() {
            });
            boolean isSucc = false;
            for (int i = 0; i < result2.length(); i++) {
                String data = result2.substring(i, i + 1);
                if (data.equals("1")) {
                    isSucc = true;
                }
            }
            if (isSucc) {
                showToast("退码成功！");
                //获取数据
                getjilu1Service.load(new GetJilu1Params(MyData.user1));
            } else {
                showToast("退码失败！");
            }

        }
    };

    @Override
    protected void initParams() {
        if (sp_user.getBoolean("islogin", false)) {
            //如果登陆 则加载界面
            initView();
        } else {
            //如果没有登陆 则跳转到登陆界面
            startActivity(new Intent(MainActivity.this, Login.class));
            finish();
        }


    }

    @Override
    protected View FullScreen() {
        return layout;
    }


    private void initView() {

        //随便设置一个值
        textIco = num_ico;
        text = num;
        MyData.user1 = sp_user.getString("user1", "");
        MyData.mi = sp_user.getString("mi", "");

        //获取数据
        getjilu1Service.load(new GetJilu1Params(MyData.user1));

        //初始化列表
        adapter = new MainAdapter(this, dataList);
        listView.setAdapter(adapter);

    }

    @Event({R.id.num_layout, R.id.gold_layout, R.id.numxian, R.id.numdao,
            R.id.numx, R.id.num0, R.id.numo, R.id.num1, R.id.num2, R.id.num3, R.id.num4,
            R.id.num5, R.id.num6, R.id.num7, R.id.num8, R.id.num9, R.id.numqing, R.id.numok,
            R.id.dao1, R.id.dao2, R.id.dao3, R.id.dao4, R.id.qian1, R.id.qian2, R.id.qian3,
            R.id.bai1, R.id.bai2, R.id.bai3, R.id.shi1, R.id.shi2, R.id.shi3, R.id.ge1, R.id.ge2,
            R.id.ge3, R.id.tuima, R.id.shengcheng, R.id.mingxi, R.id.jingcai})
    private void onClick(View view) {
        switch (view.getId()) {
            case R.id.num_layout:
                textIco.setVisibility(View.INVISIBLE);
                text = num;
                textIco = num_ico;
                text.setText("");
                textIco.setVisibility(View.VISIBLE);
                break;
            case R.id.gold_layout:
                textIco.setVisibility(View.INVISIBLE);
                text = gold;
                textIco = gold_ico;
                text.setText("");
                hasPoint = false;
                jine = false;
                textIco.setVisibility(View.VISIBLE);
                break;
            case R.id.numxian:
                if (type.getText().toString().equals("现")) {
                    type.setText("");
                } else {
                    type.setText("现");
                }
                break;
            case R.id.numdao:
                if (type.getText().toString().equals("倒")) {
                    type.setText("");
                } else {
                    type.setText("倒");
                }
                break;
            case R.id.numo:
                //当前选择的是金额输入框
                //不是第一位
                //没有小数点
                if (text == gold && !hasPoint && text.getText().toString().length() > 0) {
                    text.setText(text.getText().toString() + ".");
                    hasPoint = true;
                }
                break;
            case R.id.num0:
            case R.id.num1:
            case R.id.num2:
            case R.id.num3:
            case R.id.num4:
            case R.id.num5:
            case R.id.num6:
            case R.id.num7:
            case R.id.num8:
            case R.id.num9:
                if (text == num && text.getText().length() < 3 || text == gold) {
                    if (jine && text == gold) {
                        text.setText(view.getTag().toString());
                        jine = false;
                    } else {
                        text.setText(text.getText().toString() + view.getTag().toString());
                    }
                } else if (text.length() == 3) {
                    text.setText(text.getText().toString() + view.getTag().toString());
                    text = gold;
                    textIco.setVisibility(View.INVISIBLE);
                    textIco = gold_ico;
                    textIco.setVisibility(View.VISIBLE);
                    jine = true;
                }
                break;
            case R.id.numx:
                if (text == num) {
                    text.setText(text.getText().toString() + view.getTag().toString());
                    if (text.getText().toString().length() >= 4) {
                        text = gold;
                        textIco.setVisibility(View.INVISIBLE);
                        textIco = gold_ico;
                        textIco.setVisibility(View.VISIBLE);
                        jine = true;
                    }
                }
                break;

            case R.id.numqing:
                updateZt1Service.load(new UpdateZt1Params(allid));
                break;

            case R.id.numok:
                xiaZhu();
                textIco.setVisibility(View.INVISIBLE);
                text = num;
                textIco = num_ico;
                text.setText("");
                textIco.setVisibility(View.VISIBLE);
                break;

            //倒的状态
            case R.id.dao1:
                dao1 = !dao1;
                ((ImageView) view).setImageResource(dao1 ? R.mipmap.dao1s : R.mipmap.dao1);
                break;
            case R.id.dao2:
                dao2 = !dao2;
                ((ImageView) view).setImageResource(dao2 ? R.mipmap.dao2s : R.mipmap.dao2);
                break;
            case R.id.dao3:
                dao3 = !dao3;
                ((ImageView) view).setImageResource(dao3 ? R.mipmap.dao3s : R.mipmap.dao3);
                break;
            case R.id.dao4:
                dao4 = !dao4;
                ((ImageView) view).setImageResource(dao4 ? R.mipmap.dao3s : R.mipmap.dao3);
                break;

            //千百十个的状态
            case R.id.qian1:
                qian1 = !qian1;
                ((ImageView) view).setImageResource(qian1 ? R.mipmap.qian11 : R.mipmap.qian1);
                break;
            case R.id.qian2:
                qian2 = !qian2;
                ((ImageView) view).setImageResource(qian2 ? R.mipmap.qian11 : R.mipmap.qian1);
                break;
            case R.id.qian3:
                qian3 = !qian3;
                ((ImageView) view).setImageResource(qian3 ? R.mipmap.qian111 : R.mipmap.qian1);
                break;
            case R.id.bai1:
                bai1 = !bai1;
                ((ImageView) view).setImageResource(bai1 ? R.mipmap.bai11 : R.mipmap.bai1);
                break;
            case R.id.bai2:
                bai2 = !bai2;
                ((ImageView) view).setImageResource(bai2 ? R.mipmap.bai11 : R.mipmap.bai1);
                break;
            case R.id.bai3:
                bai3 = !bai3;
                ((ImageView) view).setImageResource(bai3 ? R.mipmap.bai11 : R.mipmap.bai1);
                break;
            case R.id.shi1:
                shi1 = !shi1;
                ((ImageView) view).setImageResource(shi1 ? R.mipmap.shi11 : R.mipmap.shi1);
                break;
            case R.id.shi2:
                shi2 = !shi2;
                ((ImageView) view).setImageResource(shi2 ? R.mipmap.shi11 : R.mipmap.shi1);
                break;
            case R.id.shi3:
                shi3 = !shi3;
                ((ImageView) view).setImageResource(shi3 ? R.mipmap.shi11 : R.mipmap.shi1);
                break;
            case R.id.ge1:
                ge1 = !ge1;
                ((ImageView) view).setImageResource(ge1 ? R.mipmap.ge11 : R.mipmap.ge1);
                break;
            case R.id.ge2:
                ge2 = !ge2;
                ((ImageView) view).setImageResource(ge2 ? R.mipmap.ge11 : R.mipmap.ge1);
                break;
            case R.id.ge3:
                ge3 = !ge3;
                ((ImageView) view).setImageResource(ge3 ? R.mipmap.ge11 : R.mipmap.ge1);
                break;

            case R.id.tuima:
                if (!removeList.isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示：");
                    builder.setMessage("是否批量删除？");
                    builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                            tuimaG3Service.load(new TuimaG3Params(MyData.user1, MyData.mi, removeList));
                        }
                    });
                    builder.setNegativeButton("取消", null);
                    Dialog dialog = builder.create();
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                    dialog.show();
                } else {
                    showToast("没有码可以退！");
                }
                break;
            case R.id.shengcheng:
                shengCheng();
                break;

            case R.id.mingxi:
                startActivity(new Intent(MainActivity.this, MingXi.class));
                break;
            case R.id.jingcai:
                startActivity(new Intent(MainActivity.this, JingCai.class));
                break;
        }
    }

    private void shengCheng() {

    }

    private void xiaZhu() {
        String number = num.getText().toString();
        String money = gold.getText().toString();
        String tag = type.getText().toString();
        xiaZhuService.load(new XiaZhuParams(MyData.user1, MyData.mi, number, money, tag));

    }


    public static void check(Map<String, String> map) {
        if (!removeList.remove(map)) {
            removeList.add(map);
        }
    }
}
