package com.example.testjetpack.db

import com.example.testjetpack.db.entity.CommentEntity
import com.example.testjetpack.db.entity.ProductEntity
import java.util.*
import java.util.concurrent.TimeUnit

class DataGenerator {
    companion object{
        val FIRST = arrayOf(
            "Special edition", "New", "Cheap", "Quality", "Used"
        )
        val SECOND = arrayOf( "Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle")
        val DESCRIPTION = arrayOf("is finally here", "is recommended by Stan S. Stanman",
            "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine")
        val COMMENTS = arrayOf("Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6")

        fun generateProducts(): List<ProductEntity> {
            val products: MutableList<ProductEntity> =
                ArrayList<ProductEntity>(FIRST.size * SECOND.size)
            val rnd = Random()
            for (i in FIRST.indices) {
                for (j in SECOND.indices) {
                    val product = ProductEntity()
                    product.setName(
                        FIRST[i] + " " + SECOND[j]
                    )
                    product.setDescription(
                        product.getName().toString() + " " + DESCRIPTION[j]
                    )
                    product.setPrice(rnd.nextInt(240))
                    product.setId(FIRST.size * i + j + 1)
                    products.add(product)
                }
            }
            return products!!
        }

        fun generateCommentsForProducts(
            products: List<ProductEntity>
        ): List<CommentEntity> {
            val comments: MutableList<CommentEntity> =
                ArrayList<CommentEntity>()
            val rnd = Random()
            for (product in products) {
                val commentsNumber = rnd.nextInt(5) + 1
                for (i in 0 until commentsNumber) {
                    val comment = CommentEntity()
                    comment.setProductId(product.getId())
                    comment.setText(
                        COMMENTS[i] + " for " + product.getName()
                    )
                    comment.setPostedAt(
                        Date(
                            System.currentTimeMillis()
                                    - TimeUnit.DAYS.toMillis(commentsNumber - i.toLong()) + TimeUnit.HOURS.toMillis(
                                i.toLong()
                            )
                        )
                    )
                    comments.add(comment)
                }
            }
            return comments
        }    }
}