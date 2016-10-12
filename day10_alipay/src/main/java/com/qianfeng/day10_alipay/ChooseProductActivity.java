package com.qianfeng.day10_alipay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.qianfeng.day10_alipay.bean.Product;
import com.qianfeng.day10_alipay.db.DBUtils;
import com.qianfeng.day10_alipay.db.ProductDao;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品选购界面
 */
public class ChooseProductActivity extends AppCompatActivity {

    List<Product> mProducts = null;
    private ProductDao mProductDao;
    private ListView mProductLv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_product);

        initData();

        initViews();
    }

    private void initViews() {
        mProductLv = (ListView)findViewById(R.id.product_list_lv);
        mProductLv.setAdapter(new ProductAdapter());
    }

    private void initData() {
        mProducts = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Product product = new Product();
//            product.set_id(Long.valueOf(i));
            product.setProductId(System.currentTimeMillis());
            product.setProductName("商品"+i);
            product.setProductNum(1);
            product.setProductPrice(0.01f);
            mProducts.add(product);
        }

        mProductDao = DBUtils.getProductDao(this);
    }

    public void onGotoCart(View view) {
        startActivity(new Intent(this,CartActivity.class));
    }

    /**
     * 商品列表的适配器
     */
    class ProductAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mProducts.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            ViewHolder holder;
            if(view == null){
                view = LayoutInflater.from(ChooseProductActivity.this).inflate(R.layout.product_item,parent,false);
                holder = new ViewHolder(view);
            }else{
                holder = (ViewHolder) view.getTag();
            }
            final Product product = mProducts.get(position);
            holder.mProductNameTv.setText(product.getProductName());
            holder.mProductPriceTv.setText("$"+product.getProductPrice());
            holder.mBuyProductBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //插入商品到数据库中
                    mProductDao.insertOrReplace(product);
                    Toast.makeText(ChooseProductActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        class ViewHolder{
            TextView mProductNameTv;
            TextView mProductPriceTv;
            Button mBuyProductBtn;
            public ViewHolder(View view){
                mProductNameTv = (TextView)view.findViewById(R.id.product_name_tv);
                mProductPriceTv = (TextView)view.findViewById(R.id.product_price_tv);
                mBuyProductBtn = (Button)view.findViewById(R.id.product_buy_btn);
                view.setTag(this);
            }
        }
    }
}
