class CreateMenus < ActiveRecord::Migration
  def change
    create_table :menus do |t|
      t.string :menu_img1
      t.string :menu_img2

      t.timestamps null: false
    end
  end
end
