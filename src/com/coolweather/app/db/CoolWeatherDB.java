package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {
	
	//数据库名
	public static final String DB_NAME = "cool_weather";
	
	//数据库的版本
	public static final int VERSION = 1;
	
	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;
	
	
	//将构造方法私有化
	private CoolWeatherDB(Context context){
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context , DB_NAME , null , VERSION);
		db = dbHelper.getWritableDatabase();
	}
	
	
	//获取CoolWeatherDB的实例,即返回已经经过初始化的变量coolWeatherDB
	public synchronized static CoolWeatherDB getInstance(Context context){
		
		if(coolWeatherDB == null){ //如果当前coolWeatherDB还不存在就新初始化一个
			coolWeatherDB = new CoolWeatherDB(context);
		}
		
		return coolWeatherDB;
	}
	
	
	//将Province的实例存储到数据库
	public void saveProvince(Province province){
		if(province != null){
			ContentValues values = new ContentValues();
			values.put("province_name" , province.getProvinceName());
			values.put("province_code" , province.getProvinceCode());
			db.insert("Province" , null , values); //第一个参数是表名
		}
	}
	
	
	//从数据库读取全国所有的省份Province信息
	public List<Province> loadProvinces(){
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query( "Province" , null , null , null , null , null , null );
		
		if(cursor.moveToFirst()){
			do{
				Province province = new Province();
				province.setId( cursor.getInt(cursor.getColumnIndex("id")) );
				province.setProvinceName( cursor.getString(cursor.getColumnIndex("province_name")) );
				province.setProvinceCode( cursor.getString(cursor.getColumnIndex("province_code")) );
				list.add(province);
			}while(cursor.moveToNext());
		}//这个if语句结束之后，list这个集合变量中就是数据库中所有的省份信息了，将这个值返回即可
		
		if(cursor != null){
			cursor.close(); //此时，cursor就使用完毕了，结束之前先将它关掉
		}
		
		return list;
	}
	
	
	//将City的实例存储到数据库
	public void saveCity(City city){
		if(city != null){
			ContentValues values = new ContentValues();
			values.put("city_name" , city.getCityName());
			values.put("city_code" , city.getCityCode());
			values.put("province_id" , city.getProvinceId());
			db.insert("City" , null , values); //第一个参数是表名
		}
	}
	
	
	//从数据库读取某省份下面所有的城市City信息
	public List<City> loadCities(int provinceId){
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query( "City" , null , "province_id=?" , new String[]{String.valueOf(provinceId)} , null , null , null );
		
		if(cursor.moveToFirst()){
			do{
				City city = new City();
				city.setId( cursor.getInt(cursor.getColumnIndex("id")) );
				city.setCityName( cursor.getString(cursor.getColumnIndex("city_name")) );
				city.setCityCode( cursor.getString(cursor.getColumnIndex("city_code")) );
				city.setProvinceId(provinceId);
				list.add(city);
			}while(cursor.moveToNext());
		}//这个if语句结束之后，list这个集合变量中就是数据库中所有的城市信息了，将这个值返回即可
		
		if(cursor != null){
			cursor.close(); //此时，cursor就使用完毕了，结束之前先将它关掉
		}
		
		return list;
	}
	
	
	//将County的实例存储到数据库
	public void saveCounty(County county){
		if(county != null){
			ContentValues values = new ContentValues();
			values.put("county_name" , county.getCountyName());
			values.put("county_code" , county.getCountyCode());
			values.put("city_id" , county.getCityId());
			db.insert("County" , null , values); //第一个参数是表名
		}
	}
	
	
	//从数据库读取某城市下面所有的县County信息
	public List<County> loadCounties(int cityId){
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query( "County" , null , "city_id=?" , new String[]{String.valueOf(cityId)} , null , null , null );
		
		if(cursor.moveToFirst()){
			do{
				County county = new County();
				county.setId( cursor.getInt(cursor.getColumnIndex("id")) );
				county.setCountyName( cursor.getString(cursor.getColumnIndex("county_name")) );
				county.setCountyCode( cursor.getString(cursor.getColumnIndex("county_code")) );
				county.setCityId(cityId);
				list.add(county);
			}while(cursor.moveToNext());
		}//这个if语句结束之后，list这个集合变量中就是数据库中所有的城市信息了，将这个值返回即可
		
		if(cursor != null){
			cursor.close(); //此时，cursor就使用完毕了，结束之前先将它关掉
		}
		
		return list;
	}

}
