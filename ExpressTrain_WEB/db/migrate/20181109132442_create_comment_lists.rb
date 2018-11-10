class CreateCommentLists < ActiveRecord::Migration
  def change
    create_table :comment_lists do |t|
      t.integer :store_num
      t.integer :comment_num

      t.timestamps null: false
    end
  end
end
