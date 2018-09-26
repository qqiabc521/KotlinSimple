package com.ljj.comm.greendao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.ljj.comm.entity.Relationship;
import com.ljj.comm.entity.UserEntity.RelationshipConverter;

import com.ljj.comm.entity.UserEntity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "USER_ENTITY".
*/
public class UserEntityDao extends AbstractDao<UserEntity, Long> {

    public static final String TABLENAME = "USER_ENTITY";

    /**
     * Properties of entity UserEntity.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property Age = new Property(2, int.class, "age", false, "AGE");
        public final static Property Email = new Property(3, String.class, "email", false, "EMAIL");
        public final static Property PhoneNumber = new Property(4, String.class, "phoneNumber", false, "PHONE_NUMBER");
        public final static Property Description = new Property(5, String.class, "description", false, "DESCRIPTION");
        public final static Property RelationShip = new Property(6, Integer.class, "relationShip", false, "RELATION_SHIP");
    }

    private final RelationshipConverter relationShipConverter = new RelationshipConverter();

    public UserEntityDao(DaoConfig config) {
        super(config);
    }
    
    public UserEntityDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"USER_ENTITY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"NAME\" TEXT UNIQUE ," + // 1: name
                "\"AGE\" INTEGER NOT NULL ," + // 2: age
                "\"EMAIL\" TEXT," + // 3: email
                "\"PHONE_NUMBER\" TEXT," + // 4: phoneNumber
                "\"DESCRIPTION\" TEXT," + // 5: description
                "\"RELATION_SHIP\" INTEGER);"); // 6: relationShip
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"USER_ENTITY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, UserEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getAge());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(5, phoneNumber);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(6, description);
        }
 
        Relationship relationShip = entity.getRelationShip();
        if (relationShip != null) {
            stmt.bindLong(7, relationShipConverter.convertToDatabaseValue(relationShip));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, UserEntity entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
        stmt.bindLong(3, entity.getAge());
 
        String email = entity.getEmail();
        if (email != null) {
            stmt.bindString(4, email);
        }
 
        String phoneNumber = entity.getPhoneNumber();
        if (phoneNumber != null) {
            stmt.bindString(5, phoneNumber);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(6, description);
        }
 
        Relationship relationShip = entity.getRelationShip();
        if (relationShip != null) {
            stmt.bindLong(7, relationShipConverter.convertToDatabaseValue(relationShip));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public UserEntity readEntity(Cursor cursor, int offset) {
        UserEntity entity = new UserEntity( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.getInt(offset + 2), // age
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // email
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // phoneNumber
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // description
            cursor.isNull(offset + 6) ? null : relationShipConverter.convertToEntityProperty(cursor.getInt(offset + 6)) // relationShip
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, UserEntity entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setAge(cursor.getInt(offset + 2));
        entity.setEmail(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPhoneNumber(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDescription(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setRelationShip(cursor.isNull(offset + 6) ? null : relationShipConverter.convertToEntityProperty(cursor.getInt(offset + 6)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(UserEntity entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(UserEntity entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(UserEntity entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}