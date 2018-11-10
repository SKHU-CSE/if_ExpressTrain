class HomeController < ApplicationController
  def map
    @stores = Store.all
  end

  def list
    if (params[:category] == nil) && (params[:card] == nil) && (params[:location] == nil)   # 체크 X
      @stores = Store.all
    elsif(params[:category] != nil) && (params[:card] == nil) && (params[:location] == nil) #카테고리만 체크
      @stores = Store.where({ category: params[:category]})
    end
  end

  def notice
  end
end
