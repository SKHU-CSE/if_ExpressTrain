class CreateStores < ActiveRecord::Migration
  def change
    create_table :stores do |t|
      t.integer :category_id
      t.string :store_name
      t.string :address
      t.string :phone
      t.string :longitude
      t.string :latitude
      t.string :photo
      # t.integer :store_type

      t.timestamps null: false
    end
  end
end
