package com.example.addressbook;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

public class FileAccessService {

    ObjectMapper mapper = new ObjectMapper();
    Context context;


    public FileAccessService(Context context){this.context = context;}

    public AddressBook loadRecords(String fileName) {
        File path = context.getExternalFilesDir(null);
        File file = new File (path, fileName);

        AddressBook returnAB = new AddressBook();

        try {
            //Read Json File and Creates List Of BaseContacts
//            List<BaseContact> myObjects = mapper.readValue(file, mapper.getTypeFactory().constructCollectionType(List.class, BaseContact.class));
            returnAB = mapper.readValue(file,AddressBook.class);

            return returnAB;
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return returnAB;
    }

    public void saveRecords(String fileName, AddressBook ab) throws IOException {

        File path = context.getExternalFilesDir(null);
        File file = new File(path,fileName);

        //saves
        try {
            //writer.write.writeValue(new File("/Users/summerguijarro/eclipse-workspace/AddressBook/src/AddressBook/Contacts.json"),ab.getContactList());
//            mapper.writerFor(new TypeReference<List<BaseContact>>() {}).writeValue(file, ab.getContactList());
            mapper.writerWithDefaultPrettyPrinter().writeValue(file, ab);
        }catch(IOException e) {
            throw e;
        }
    }
}
