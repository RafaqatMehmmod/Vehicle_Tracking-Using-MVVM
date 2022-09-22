package com.example.mvvm_project;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

public class Firebase_Initialize {



    //Initialize Variable
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private String UID;
    private FirebaseStorage storageReference;

    public DatabaseReference getDatabaseReference() {
        return databaseReference;
    }

    private DatabaseReference databaseReference;


    //Create private Constructor because not create the object for every class
    private Firebase_Initialize() { }

    //volatile mean that both thread Foreground and Background access this class
    private static volatile Firebase_Initialize INSTANCE =null;

    //Crete Method for creating and accessing the method
    //Working of this Method
    //2 Thread a gy ha at the same time 1st enter ho ga block ma ager object nahe buna class ka wo object bana da ga
    //r return kar da ga fir 2 thread enter ho ga wo daka ga ager object bun gaya ha to wo direct return kar da ga
    public static Firebase_Initialize getInstance()
    {
        if (INSTANCE==null)
        {
            synchronized (Firebase_Initialize.class)
            {
                if (INSTANCE==null)
                {
                    INSTANCE=new Firebase_Initialize();

                    //Firebase Code
                    INSTANCE.auth=FirebaseAuth.getInstance();
                    INSTANCE.UID=INSTANCE.auth.getUid();
                    INSTANCE.firebaseDatabase=FirebaseDatabase.getInstance();
                    INSTANCE.storageReference= FirebaseStorage.getInstance();
                    INSTANCE.databaseReference=FirebaseDatabase.getInstance().getReference();
                }
            }
        }

        return INSTANCE;
    }

    //Getter of Variable
    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseDatabase getFirebaseDatabase() {
        return firebaseDatabase;
    }

    public String getUID() {
        return UID;
    }

    public FirebaseStorage getStorageReference() {
        return storageReference;
    }

}
