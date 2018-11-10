class CreateApplies < ActiveRecord::Migration
  def change
    create_table :applies do |t|
      t.integer :member_id
      t.integer :category_id
      t.string :store_name
      t.string :address
      t.string :phone
      t.integer :check, default: 0 #0: 승인대기 1: 승인완료
      t.string :photo

      t.timestamps null: false
    end
  end
end
