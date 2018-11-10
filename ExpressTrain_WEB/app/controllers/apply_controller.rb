class ApplyController < ApplicationController
  def index
    @applies = Apply.all
  end
  
  def new
  end

  def create
    if member_signed_in?
      @apply = Apply.new
      @apply.member_id = Member.find(current_member.id)
      @apply.store_name = params[:store_name]
      @apply.category_id = params[:category_id]
      @apply.address = params[:address]
      @apply.phone = params[:phone]
      @apply.save
    end
    redirect_to '/apply/index'
  end
  
  def update
    @apply = Apply.find(params[:apply_id])
    @apply.check = 1
    @apply.save
    
    redirect_to '/apply/index'
  end
end
