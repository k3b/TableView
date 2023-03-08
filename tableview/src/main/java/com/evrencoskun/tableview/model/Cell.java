/*
 * MIT License
 *
 * Copyright (c) 2021 Evren Coşkun
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.evrencoskun.tableview.model;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.evrencoskun.tableview.filter.IFilterableModel;
import com.evrencoskun.tableview.sort.ISortableModel;

/**
 * Created by evrencoskun on 11/06/2017.
 */

public class Cell<POJO extends IModelWithId> implements ISortableModel, IFilterableModel {
    @NonNull
    private final POJO mData;
    private final IColumnValueProvider columnValueProvider;

    /**
     *
     * @param data the row where the cell data belongs to.
     * @param columnValueProvider that gets the cell value out of the data-row
     */
    public Cell(@NonNull POJO data, IColumnValueProvider columnValueProvider) {
        this.mData = data;
        this.columnValueProvider = columnValueProvider;
    }

    /**
     * This is necessary for sorting process. Id must be unique per data row.
     * See {@link ISortableModel}.
     */
    @NonNull
    @Override
    public String getId() {
        return mData.getId();
    }

    /**
     * This is necessary for sorting process.
     * See {@link ISortableModel}.
     */
    @Nullable
    @Override
    public Object getContent(int column) {
        return columnValueProvider.get(mData);
    }

    @Nullable
    public POJO getData() {
        return mData;
    }

    /**
     * See {@link IFilterableModel}.
     */
    @NonNull
    @Override
    public String getFilterableKeyword() {
        return getContent(0).toString();
    }
}
