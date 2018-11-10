class CategoryController < ApplicationController
  def create
    @category = Category.new
    @category.category_name = params[:category_name]
    @category.save
  end
  
  def delete
  end
end
