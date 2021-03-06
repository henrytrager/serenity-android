/**
 * The MIT License (MIT)
 * Copyright (c) 2012 David Carver
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 *
 * The above copyright notice and this permission notice shall be included
 * in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS
 * OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS
 * OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
 * WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF
 * OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package us.nineworlds.serenity.volley;

import javax.inject.Inject;
import javax.inject.Singleton;

import us.nineworlds.plex.rest.model.impl.MediaContainer;
import us.nineworlds.serenity.core.OkHttpStack;
import us.nineworlds.serenity.injection.ApplicationContext;
import us.nineworlds.serenity.injection.BaseInjector;
import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

@Singleton
public class VolleyUtils extends BaseInjector {

	@Inject
	@ApplicationContext
	Context context;

	private final RequestQueue queue;

	public VolleyUtils() {
		super();
		queue = Volley.newRequestQueue(context, new OkHttpStack());
	}

	public RequestQueue getRequestQueue() {
		return queue;
	}

	public Request volleyXmlGetRequest(String url, Response.Listener response,
			Response.ErrorListener error) {
		SimpleXmlRequest<MediaContainer> request = new SimpleXmlRequest<MediaContainer>(
				Request.Method.GET, url, MediaContainer.class, response, error);
		if (queue == null) {
			Log.e("VolleyUtils", "Initialize Request Queue!");
			return null;
		}
		return queue.add(request);
	}

	public Request volleyJSonGetRequest(String url, Response.Listener response,
			Response.ErrorListener error) {
		JsonObjectRequest request = new JsonObjectRequest(url, null, response,
				error);
		if (queue == null) {
			Log.e("VolleyUtils", "Initialize Request Queue!");
			return null;
		}
		return queue.add(request);
	}

}
