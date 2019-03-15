package com.course.recyclerview3;

import java.io.DataInputStream;
import java.io.DataOutputStream;

public abstract class Item {
    public Item(DataInputStream stream) {

    }

    public Item() {

    }

    public abstract void serialize(DataOutputStream storage);


}
