package com.coolweather.app.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class CoolWeatherOpenHelper extends SQLiteOpenHelper{
	
	//建立“省”的信息表
	public static final String CREATE_PROVINCE = "create table Province ("
			+ "id integer primary key autoincrement, "
			+ "province_name text"
			+ "province_code text)";
	
	//建立“市”的信息表
	public static final String CREATE_CITY = "create table City ("
			+ "id integer primary key autoincrement, "
			+ "city_name text"
			+ "city_code text"
			+ "province_id integer)";
	
	//建立“县”的信息表
	public static final String CREATE_COUNTY = "create table County ("
			+ "id integer primary key autoincrement, "
			+ "county_name text"
			+ "county_code text"
			+ "city_id integer)";
	
	//构造函数
	public CoolWeatherOpenHelper(Context context , String name , CursorFactory factory , int version){
		super(context, name, factory, version);
	}
	
	//自动被调用的onCreate()函数，执行创建表的操作
	@Override
	public void onCreate(SQLiteDatabase db){
		db.execSQL(CREATE_PROVINCE);
		db.execSQL(CREATE_CITY);
		db.execSQL(CREATE_COUNTY);
	}
	
	//当信息发生更新的时候自动调用
	@Override
	public void onUpgrade(SQLiteDatabase db , int oldVersion , int newVersion){
		//先暂时什么也不写
	}
	
	
	
	
	
	

}
