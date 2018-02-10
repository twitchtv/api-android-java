package com.igdb.api_android_java.wrapper

import java.util.ArrayList

/**
 * Wrapper.Parameters updated to Kotlin from java
 * Lets users build queries for the wrapper
 *
 * Created at: 2017-09-21
 * Updated at: 2018-02-10
 *
 * Created by Filip
 *
 **/
class Parameters {
    private val filters = ArrayList<String>()
    private var offset  = ""
    private var ids     = ""
    private var fields  = ""
    private var expand  = ""
    private var limit   = ""
    private var order   = ""
    private var search  = ""
    private var scroll  = ""
    private var query   = ""

    fun addFilter(filter: String): Parameters {
        filters.add(("&filter" + filter).replace(" ".toRegex(), ""))
        return this
    }

    fun addIds(ids: String): Parameters {
        this.ids = ids.replace(" ".toRegex(), "")
        return this
    }

    fun addOffset(offset: String): Parameters {
        this.offset = "&offset=" + offset.replace(" ".toRegex(), "")
        return this
    }

    fun addFields(fields: String): Parameters {
        this.fields = fields.replace(" ".toRegex(), "")
        return this
    }

    fun addExpand(expand: String): Parameters {
        this.expand = "&expand=" + expand.replace(" ".toRegex(), "")
        return this
    }

    fun addLimit(limit: String): Parameters {
        this.limit = "&limit=" + limit.replace(" ".toRegex(), "")
        return this
    }

    fun addOrder(order: String): Parameters {
        this.order = "&order=" + order.replace(" ".toRegex(), "")
        return this
    }

    fun addSearch(search: String): Parameters {
        this.search = "?search=" + search.replace(" ".toRegex(), "%20")
        return this
    }

    fun addScroll(scroll: String): Parameters {
        this.scroll = "&scroll=" + scroll.replace(" ".toRegex(), "")
        return this
    }

    fun buildQuery(endpoint: Endpoint): String {
        var filter = ""
        for (s in filters) {
            filter += s
        }

        query = endpoint.toString().toLowerCase()
        query += "/" + ids + search

        if (fields !== "" && search !== "") {
            query += "&fields=" + fields
        } else if (!fields.isEmpty()) {
            query += "?fields=" + fields
        } else {
            query += "?fields=*"
        }

        query += filter + expand + order + limit + offset + scroll
        return query
    }

}
