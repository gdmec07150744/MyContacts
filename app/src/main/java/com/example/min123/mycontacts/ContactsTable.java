package com.example.min123.mycontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.Vector;

/**
 * Created by min123 on 2016/10/26.
 */
public class ContactsTable {
    private final static String TABLENAME="contactsTable";
    private MyDB db;
    public ContactsTable(Context context){
        db=new MyDB(context);
        if(!db.isTableExits(TABLENAME)) {
            String createTableSql = "CREATE TABLE IF NOT EXISTS" + TABLENAME + "(id_DB integer" +
                    "private key AUTOINCREMENT," +
                    User.NAME + "VARCHAR," +
                    User.PHONE + "VARCHAR," +
                    User.QQ + "VARCHAR," +
                    User.DANWEI + "VARCHAR," +
                    User.ADDRESS + "VARCHAR";
            db.createTable(createTableSql);
        }
    }
    public boolean addData(User user){
        ContentValues values=new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.PHONE,user.getPhone());
        values.put(User.QQ,user.getQq());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.ADDRESS,user.getAddress());
        return db.save(TABLENAME,values);
    }
    public User[] getAllUser(){
        Vector<User> v=new Vector<>();
        Cursor cursor=null;
        try{
            cursor=db.find("select * from "+TABLENAME ,null);
            while(cursor.moveToNext()){
                User temp=new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setPhone(cursor.getString(cursor.getColumnIndex(User.PHONE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                v.add(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
                db.closeConnection();
            }
            if(v.size()>0){
                return v.toArray(new User[]{});

            }else{
                User[] users=new User[1];
                User user=new User();
                user.setName("木有结果");
                users[0]=user;
                return users;
            }
        }
    }
    public User getUserByID(int id){
        Cursor cursor=null;
        User temp=new User();
        try{
            cursor=db.find("select * from "+TABLENAME+" where "+"id_DB=?",new String[]{id+""});
            cursor.moveToNext();
            temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
            temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
            temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
            temp.setPhone(cursor.getString(cursor.getColumnIndex(User.PHONE)));
            temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
            temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        return null;
    }
    public boolean updateUser(User user){
        ContentValues values=new ContentValues();
        values.put(User.NAME,user.getName());
        values.put(User.PHONE,user.getPhone());
        values.put(User.DANWEI,user.getDanwei());
        values.put(User.QQ,user.getQq());
        values.put(User.ADDRESS,user.getAddress());
        return db.update(TABLENAME,values," id_DB=?",new String[]{user.getId_DB()+""});
    }
    public User[] findUserByKey(String key){
        Vector<User> v=new Vector<>();
        Cursor cursor=null;
        try{
            while(cursor.moveToNext()){
                cursor=db.find("select * from "+TABLENAME+" where "+
                        User.NAME+" like '%"+key+"%'"+
                        " or "+User.PHONE+" like '"+key+"'"+
                        " or "+User.QQ+" like '"+key+"'",null);
                User temp=new User();
                temp.setId_DB(cursor.getInt(cursor.getColumnIndex("id_DB")));
                temp.setName(cursor.getString(cursor.getColumnIndex(User.NAME)));
                temp.setAddress(cursor.getString(cursor.getColumnIndex(User.ADDRESS)));
                temp.setPhone(cursor.getString(cursor.getColumnIndex(User.PHONE)));
                temp.setDanwei(cursor.getString(cursor.getColumnIndex(User.DANWEI)));
                temp.setQq(cursor.getString(cursor.getColumnIndex(User.QQ)));
                v.add(temp);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            if(cursor!=null){
                cursor.close();
            }
            db.closeConnection();
        }
        if(v.size()>0){
            return v.toArray(new User[]{});

        }else{
            User[] users=new User[1];
            User user=new User();
            user.setName("木有结果");
            users[0]=user;
            return users;
        }
    }
    public boolean deleteByUser(User user){
        return  db.delete(TABLENAME," id_DB=?",new String[]{user.getId_DB()+""});
    }
}
