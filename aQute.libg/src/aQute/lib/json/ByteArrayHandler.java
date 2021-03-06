package aQute.lib.json;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;

import aQute.lib.hex.*;

/**
 * 
 * Will now use hex for encoding byte arrays
 *
 */
public class ByteArrayHandler extends Handler {
	@Override
	void encode(Encoder app, Object object, Map<Object,Type> visited) throws IOException, Exception {
		StringHandler.string(app, Hex.toHexString((byte[]) object));
	}

	@Override
	Object decodeArray(Decoder r) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		ArrayList<Object> list = new ArrayList<Object>();
		r.codec.parseArray(list, Byte.class, r);
		for (Object b : list) {
			out.write(((Byte) b).byteValue());
		}
		return out.toByteArray();
	}

	@Override
	Object decode(Decoder dec, String s) throws Exception {
		return Hex.toByteArray(s);
	}
}
