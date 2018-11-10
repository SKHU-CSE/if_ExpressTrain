class MemberController < ApplicationController
    before_action :authenticate_member!
    def index
    end

    def update
        @member = Member.find_by_id(params[:id])
        if @member.update_attributes(member_params)
          redirect_to '/'
        else
          render 'edit'
        end        
    end
  
      
    def edit
        @member = Member.find_by_id(params[:id])    
        
    end
    
    private
    def member_params
         params.require(:member).permit(:name, :password, :password_confirmation,
                                        :mail)
    end
         
    
end
