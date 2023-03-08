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

package com.evrencoskun.tableview.sort;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import androidx.recyclerview.widget.DiffUtil;

import com.evrencoskun.tableview.model.IRow;

import java.util.List;

/**
 * Created by evrencoskun on 23.11.2017.
 */

public class ColumnSortCallback extends DiffUtil.Callback {
    @NonNull
    private final List<IRow<ISortableModel>> mOldCellItems;
    @NonNull
    private final List<IRow<ISortableModel>> mNewCellItems;
    private final int mColumnPosition;

    public ColumnSortCallback(@NonNull List<IRow<ISortableModel>> oldCellItems, @NonNull List<IRow<ISortableModel>>
            newCellItems, int column) {
        this.mOldCellItems = oldCellItems;
        this.mNewCellItems = newCellItems;
        this.mColumnPosition = column;
    }

    @Override
    public int getOldListSize() {
        return mOldCellItems.size();
    }

    @Override
    public int getNewListSize() {
        return mNewCellItems.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        // Control for precaution from IndexOutOfBoundsException
        if (mOldCellItems.size() > oldItemPosition && mNewCellItems.size() > newItemPosition) {
            if (mOldCellItems.get(oldItemPosition).size() > mColumnPosition && mNewCellItems.get
                    (newItemPosition).size() > mColumnPosition) {
                // Compare ids
                String oldId = mOldCellItems.get(oldItemPosition).get(mColumnPosition).getId();
                String newId = mNewCellItems.get(newItemPosition).get(mColumnPosition).getId();
                return oldId.equals(newId);
            }
        }
        return false;
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        // Control for precaution from IndexOutOfBoundsException
        if (mOldCellItems.size() > oldItemPosition && mNewCellItems.size() > newItemPosition) {
            if (mOldCellItems.get(oldItemPosition).size() > mColumnPosition && mNewCellItems.get
                    (newItemPosition).size() > mColumnPosition) {
                // Compare contents
                Object oldContent = mOldCellItems.get(oldItemPosition).get(mColumnPosition)
                        .getContent(mColumnPosition);
                Object newContent = mNewCellItems.get(newItemPosition).get(mColumnPosition)
                        .getContent(mColumnPosition);
                return ObjectsCompat.equals(oldContent, newContent);
            }
        }
        return false;
    }
}
