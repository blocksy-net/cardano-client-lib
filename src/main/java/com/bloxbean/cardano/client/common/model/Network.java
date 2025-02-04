package com.bloxbean.cardano.client.common.model;

import com.sun.jna.Structure;

import java.io.Closeable;
import java.util.List;
import java.util.Objects;

import static java.util.Arrays.asList;

public class Network extends Structure  implements Closeable {

    public static class ByReference extends Network implements Structure.ByReference {}

    public int network_id;
    public long protocol_magic;

    @Override
    protected List<String> getFieldOrder() {
        return asList("network_id", "protocol_magic");
    }
    @Override
    public void close() {
        // Turn off "auto-synch". If it is on, JNA will automatically read all fields
        // from the struct's memory and update them on the Java object. This synchronization
        // occurs after every native method call. If it occurs after we drop the struct, JNA
        // will try to read from the freed memory and cause a segmentation fault.
        setAutoSynch(false);
        // Send the struct back to rust for the memory to be freed
        //Greetings.INSTANCE.dropGreeting(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
        Network network = (Network) o;
        return network_id == network.network_id && protocol_magic == network.protocol_magic;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), network_id, protocol_magic);
    }

    @Override
    public String toString() {
        return "Network{" +
                "network_id=" + network_id +
                ", protocol_magic=" + protocol_magic +
                '}';
    }
}
